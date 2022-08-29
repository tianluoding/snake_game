package com.kob.service.impl.pk;

import com.kob.consumer.WebSocketServer;
import com.kob.service.pk.StartGameService;
import org.springframework.stereotype.Service;

@Service
public class StartGameServiceImpl implements StartGameService {
    @Override
    public String startGame(Integer aId, Integer botAId, Integer bId, Integer botBId) {
        System.out.println("start game: " + aId + ' ' + botAId + ' ' + bId + ' ' + botBId);
        WebSocketServer.startGame(aId, botAId, bId, botBId);
        return "start game success";
    }
}
