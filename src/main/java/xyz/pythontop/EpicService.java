package xyz.pythontop;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.pythontop.pojo.Game;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class EpicService {

    private final ObjectMapper mapper;
    private static final String FREE_GAMES_URL = "https://store-site-backend-static.ak.epicgames.com/freeGamesPromotions/";
    public static final String GAME_URL = "https://www.epicgames.com/store/ru/p/";
    public static final Logger LOG = LoggerFactory.getLogger(EpicService.class.getName());

    public EpicService() {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
    }

    public List<Game> findGames() {
        List<Game> games = findAllGames();
        LOG.info("All games: {}", games);
        if (games == null) return null;

        return games
                .stream()
                .filter(game ->
                        game.getEffectiveDate().isAfter(LocalDateTime.now().minusDays(7))
                                && game.getEffectiveDate().isBefore(LocalDateTime.now())
                )
                .collect(Collectors.toList());
    }

    private List<Game> findAllGames() {
        try {
            JsonNode jsonNode = mapper.readTree(new URL(FREE_GAMES_URL));
            LOG.info("Json: {}", jsonNode);
            return mapper.readValue(
                    jsonNode.findValue("elements").toString(),
                    new TypeReference<List<Game>>() {
                    }
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String createUrl(Game game) {
        return GAME_URL + game.getProductSlug();
    }
}
