package sheridan.grzelake.mygames.controller;

import sheridan.grzelake.mygames.exception.ResourceNotFoundException;
import sheridan.grzelake.mygames.model.Game;
import sheridan.grzelake.mygames.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.Link;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class GameController {

    @Autowired
    GameRepository gameRepository;

    @GetMapping("/games")
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @PostMapping("/games")
    public Game createGame(@Valid @RequestBody Game game) {
        return gameRepository.save(game);
    }

    @GetMapping("/games/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable(value = "id") Long gameId) {
        Game n = gameRepository.findById(gameId)
                .orElseThrow(() -> new ResourceNotFoundException("Game", "id", gameId));
        Link selflink = ControllerLinkBuilder.linkTo(GameController.class).slash(n.getIdentify()).withSelfRel();
        n.add(selflink);
        return new ResponseEntity<Game>(n, HttpStatus.OK);
    }

    @PutMapping("/games/{id}")
    public Game updateGame(@PathVariable(value = "id") Long gameId,
                                           @Valid @RequestBody Game gameDetails) {

        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new ResourceNotFoundException("Game", "id", gameId));

        game.setTitle(gameDetails.getTitle());
        game.setRating(gameDetails.getRating());

        Game updatedGame = gameRepository.save(game);
        return updatedGame;
    }

    @DeleteMapping("/games/{id}")
    public ResponseEntity<?> deleteGame(@PathVariable(value = "id") Long gameId) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new ResourceNotFoundException("Game", "id", gameId));

        gameRepository.delete(game);

        return ResponseEntity.ok().build();
    }
}
