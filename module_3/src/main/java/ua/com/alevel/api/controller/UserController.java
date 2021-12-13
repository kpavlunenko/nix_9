package ua.com.alevel.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.api.dto.request.UserRequestDto;
import ua.com.alevel.api.dto.response.UserResponseDto;
import ua.com.alevel.facade.UserFacade;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController extends BaseController {

    private final UserFacade userFacade;

    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAll(WebRequest request) {
        return ResponseEntity.ok(userFacade.findAll(request));
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count(WebRequest request) {
        return ResponseEntity.ok(userFacade.count(request));
    }

    @PostMapping("")
    public ResponseEntity<Boolean> createNewUser(@RequestBody UserRequestDto userRequestDto) {
        userFacade.create(userRequestDto);
        return ResponseEntity.ok(true);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> updateCompany(@PathVariable Long id, @RequestBody UserRequestDto userRequestDto) {
        userFacade.update(userRequestDto, id);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        userFacade.delete(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(true);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> details(@PathVariable Long id) {
        return ResponseEntity.ok(userFacade.findById(id));
    }
}
