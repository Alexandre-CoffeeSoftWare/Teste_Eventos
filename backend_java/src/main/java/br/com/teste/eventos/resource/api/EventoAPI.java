package br.com.teste.eventos.resource.api;

import br.com.teste.eventos.dto.EventoDTO;
import br.com.teste.eventos.dto.EventoListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
public interface EventoAPI {

    @Operation(summary = "Cadastrar novo evento", tags = {"evento"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EventoDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request - Esse status deverá ser retornado quando algum requisito não for atendido, e não devemos ter quaisquer informação sobre qual foi o requisito não atendido")})
    @PostMapping(value = "/eventos",
            produces = {"application/json"},
            consumes = {"application/json"})
    EventoDTO inserir(@RequestBody EventoDTO body);

    @Operation(summary = "Alterar evento", tags = {"evento"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EventoDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request - Esse status deverá ser retornado quando algum requisito não for atendido, e não devemos ter quaisquer informação sobre qual foi o requisito não atendido")})
    @PutMapping(value = "/eventos",
            produces = {"application/json"},
            consumes = {"application/json"})
    EventoDTO alterar(@RequestBody EventoDTO body);

    @Operation(summary = "Excluir evento", tags = {"evento"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EventoDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request - Esse status deverá ser retornado quando algum requisito não for atendido, e não devemos ter quaisquer informação sobre qual foi o requisito não atendido")})
    @DeleteMapping(value = "/eventos/{id}")
    Void excluir(@PathVariable Long id);

    @Operation(summary = "Listar eventos", tags = {"evento"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EventoDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request - Esse status deverá ser retornado quando algum requisito não for atendido, e não devemos ter quaisquer informação sobre qual foi o requisito não atendido")})
    @GetMapping(value = "/eventos")
    List<EventoListResponse> listar();

    @Operation(summary = "Buscar uma evento", tags = {"evento"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EventoDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request - Esse status deverá ser retornado quando algum requisito não for atendido, e não devemos ter quaisquer informação sobre qual foi o requisito não atendido")})
    @GetMapping(value = "/eventos/{id}")
    EventoDTO buscar(@PathVariable("id") Long id);

    @Operation(summary = "Ativar / Inativar evento", tags = {"evento"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Bad Request - Esse status deverá ser retornado quando algum requisito não for atendido, e não devemos ter quaisquer informação sobre qual foi o requisito não atendido")})
    @PutMapping(value = "/eventos/{id}/{ativo}")
    void alterarStatus(@PathVariable("id") Long id, @PathVariable("ativo") Boolean ativo);
}
