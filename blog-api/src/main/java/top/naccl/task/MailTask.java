package top.naccl.task;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.naccl.config.properties.RabbitMQConstant;
import top.naccl.entity.MailLog;
import top.naccl.entity.User;
import top.naccl.mapper.MailLogMapper;
import top.naccl.mapper.ScheduleJobMapper;
import top.naccl.service.ScheduleJobService;
import top.naccl.service.UserService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 邮件发送定时任务
 */
@Component
public class MailTask {

    @Autowired
    MailLogMapper mailLogMapper;

    @Autowired
    UserService userService;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    ScheduleJobService scheduleJobService;

    @Autowired
    ScheduleJobMapper scheduleJobMapper;


    public void syncMailTaskToDatabase(){
        // 查询状态处于投递中的数据 status=0   因为当有一个用户注册的时候，发送邮件失败时，会开启定时任务，所以就不能设置时间的限制，可能会遗漏用户
        List<MailLog> list = mailLogMapper.getStatusFailList(RabbitMQConstant.MailConstant.DELIVERING);
        list.forEach(mailLog -> {
            // 如果重试次数超过最大重复次数，更新投递状态为投递失败，不再重试
            if (RabbitMQConstant.MailConstant.MAX_TRY_COUNT < mailLog.getCount()){
                mailLogMapper.updateMailStatus(mailLog.getMsgId(),RabbitMQConstant.MailConstant.FAILURE, LocalDateTime.now());
            }
            mailLog.setCount(mailLog.getCount() + 1);
            mailLog.setUpdateTime(LocalDateTime.now());
            mailLog.setTryTime(LocalDateTime.now().plusMinutes(RabbitMQConstant.MailConstant.MSG_TIMEOUT));
            mailLogMapper.updateMail(mailLog);
            User user = userService.findUserById(mailLog.getUserId());
            // 重新发送消息
            rabbitTemplate.convertAndSend(RabbitMQConstant.MailConstant.MAIL_EXCHANGE_NAME,RabbitMQConstant.MailConstant.MAIL_ROUTING_KEY_NAME,user,new CorrelationData(RabbitMQConstant.MailConstant.MAIL_ID_PREFIX + RabbitMQConstant.DOT+  mailLog.getMsgId()));
        });
        if (list.size() == 0){
            if (scheduleJobMapper.getJobById(3L).getStatus()){
                // 停止定时任务
                scheduleJobService.updateJobStatusById(3L,false);
            }
        }
    }
}
