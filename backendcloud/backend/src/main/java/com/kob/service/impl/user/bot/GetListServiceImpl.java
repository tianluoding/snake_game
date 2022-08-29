package com.kob.service.impl.user.bot;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kob.mapper.BotMapper;
import com.kob.pojo.Bot;
import com.kob.pojo.User;
import com.kob.service.impl.utils.UserDetailsImpl;
import com.kob.service.user.bot.GetListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetListServiceImpl implements GetListService {
    @Autowired
    private BotMapper botMapper;

    @Override
    public List<Bot> getList() {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        LambdaQueryWrapper<Bot> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Bot::getUserId, user.getId());


        return botMapper.selectList(queryWrapper);
    }
}
