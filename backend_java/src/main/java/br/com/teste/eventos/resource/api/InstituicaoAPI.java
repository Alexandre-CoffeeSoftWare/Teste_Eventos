package br.com.teste.eventos.resource.api;

import br.com.teste.eventos.dto.InstituicaoDTO;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

@Validated
public interface InstituicaoAPI {

    @Operation(summary = "Cadastrar nova instituição", tags = {"instituicao"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = InstituicaoDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request - Esse status deverá ser retornado quando algum requisito não for atendido, e não devemos ter quaisquer informação sobre qual foi o requisito não atendido")})
    @PostMapping(value = "/instituicoes",
            produces = {"application/json"},
            consumes = {"application/json"})
    InstituicaoDTO inserir(@RequestBody InstituicaoDTO body);

    @Operation(summary = "Alterar instituição", tags = {"instituicao"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = InstituicaoDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request - Esse status deverá ser retornado quando algum requisito não for atendido, e não devemos ter quaisquer informação sobre qual foi o requisito não atendido")})
    @PutMapping(value = "/instituicoes",
            produces = {"application/json"},
            consumes = {"application/json"})
    InstituicaoDTO alterar(@RequestBody InstituicaoDTO body);

    @Operation(summary = "Excluir instituição", tags = {"instituicao"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = InstituicaoDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request - Esse status deverá ser retornado quando algum requisito não for atendido, e não devemos ter quaisquer informação sobre qual foi o requisito não atendido")})
    @DeleteMapping(value = "/instituicoes/{id}")
    Void excluir(@PathVariable Long id);

    @Operation(summary = "Listar instituições", tags = {"instituicao"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = InstituicaoDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request - Esse status deverá ser retornado quando algum requisito não for atendido, e não devemos ter quaisquer informação sobre qual foi o requisito não atendido")})
    @GetMapping(value = "/instituicoes")
    List<InstituicaoDTO> listar();

    @Operation(summary = "Buscar uma instituição", tags = {"instituicao"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = InstituicaoDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request - Esse status deverá ser retornado quando algum requisito não for atendido, e não devemos ter quaisquer informação sobre qual foi o requisito não atendido")})
    @GetMapping(value = "/instituicoes/{id}")
    InstituicaoDTO buscar(@PathVariable("id") Long id);

}
