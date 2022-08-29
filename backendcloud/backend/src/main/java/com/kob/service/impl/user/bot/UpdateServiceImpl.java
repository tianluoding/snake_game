package com.kob.service.impl.user.bot;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kob.mapper.BotMapper;
import com.kob.pojo.Bot;
import com.kob.pojo.User;
import com.kob.service.impl.utils.UserDetailsImpl;
import com.kob.service.user.bot.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UpdateServiceImpl implements UpdateService {
    @Autowired
    private BotMapper botMapper;

    @Override
    public Map<String, String> update(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        int bot_id = Integer.parseInt(data.get("bot_id"));
        Bot bot = botMapper.selectById(bot_id);

        String title = data.get("title");
        String description = data.get("description");
        String content = data.get("content");

        Map<String, String> map = new HashMap<>();

        if(bot == null) {
            map.put("error_message", "Bot不存在");
            return map;
        }
        if(!user.getId().equals(bot.getUserId())) {
            map.put("error_message", "没有权限修改Bot");
            return map;
        }

        if (title == null || title.length() == 0) {
            map.put("error_message", "标题不能为空");
            return map;
        }
        if(title.length() > 100) {
            map.put("error_message", "标题长度不能大于100");
            return map;
        }

        if(description == null || description.length() == 0){
            description = "这个用户很懒，什么也没有留下~";
        }
        if(description.length() > 300) {
            map.put("error_message", "Bot的描述不能大于300");
            return  map;
        }

        if(content == null || content.length() == 0) {
            map.put("error_message", "代码不能为空");
            return map;
        }
        if(content.length() > 10000) {
            map.put("error_message", "代码长度不能大于10000");
            return map;
        }

        Date now = new Date();
        bot.setTitle(title);
        bot.setDescription(description);
        bot.setContent(content);
        bot.setModifytime(now);

        LambdaQueryWrapper<Bot> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Bot::getId, bot_id);
        botMapper.update(bot, queryWrapper);

        map.put("error_message", "success");
        return map;
    }
}
