package project.java.intensive.api.domain.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import project.java.intensive.api.domain.doctor.*;



@RestController
@RequestMapping("doctor")
public class DoctorController {

    @Autowired
    private DoctorRepository repository;

    //Foi criado um metodo em que eu recebo o corpo de uma requisição que foi disparada. Nesse caso, no Insomnia, e o
    // que eu recebo eu insiro no DTO. Já com o metodo repository, eu salvo esses dados que eu recebe dentro da
    // entidade da tabela Doctor. O repository foi instanciado através da interface, que possui o Jpa, que é a conexão
    // com o banco de dados.

    @PostMapping
    @Transactional
    public ResponseEntity doctorRegister(@RequestBody @Valid DoctorRegistrationData data,
                                         UriComponentsBuilder uribuilder){
        var doctor = new Doctor(data);

        repository.save(doctor);

        var uri = uribuilder.path("/doctor/{id}").buildAndExpand(doctor.getId()).toUri();

        return ResponseEntity.created(uri).body(new DataDetailsDoctor(doctor));
    }

    //Aqui não é void, pois eu estou apenas mostrando dados. No metodo é trocado o list, pela classe page, que também
    // faz listahem dos dados. Dentro de DoctorListingData, eu sleecionei apenas os parametros que eu quero mostrar
    // na requisição. Continua sendo um metodo so que com essa classe trazendo o dto dos dados que eu preciso.
    // Realizo uma ordenação e paginação dos dados. O retorno é trazendo apenas os médicos ativos dentro da tabela do
    // banco de dados e converto para o DTO doctorListingData, para mostrar apenas os dados que eu quero
    //repository.findAllByActiveTrue(pageable): Chama um metodo no repositório que busca apenas médicos ativos
    // (active = true) e retorna uma página (Page<Doctor>).
    //.map(DoctorListingData::new): Converte cada Doctor encontrado em um objeto DoctorListingData para enviar uma resposta mais enxuta.

    @GetMapping
    public ResponseEntity<Page<DoctorListingData>> listing(@PageableDefault(size =10, sort = {"name"}) Pageable pageable){
       var page =  repository.findAllByActiveTrue(pageable).map(DoctorListingData::new);

       return ResponseEntity.ok(page);
    }

    //@Transactional é para quando é realizado mudança de dados da api e banco de dados, faz essa conexão. No metodo
    // de update, recebo um corpo da requisição somente os dados que eu estabeleci no dto, e faço validação dos mesmo
    // . Crio uma variável que eu chamo da entidade os registros pelo id para realizar a mudança dos dados. Com o
    // metodo updateinfo, que eu realizo a mudanças das informações

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid DoctorUpdateData data){
        var doctor = repository.getReferenceById(data.id());
        doctor.updateInfo(data);

        return  ResponseEntity.ok(new DataDetailsDoctor(doctor));
    }

    //Pelo id eu desativo um cadastro que está ativo na entidade

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete (@PathVariable Long id){
        //repository.deleteById(id);
        var doctor = repository.getReferenceById(id);
        doctor.delete();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity details (@PathVariable Long id){
        //repository.deleteById(id);
        var doctor = repository.getReferenceById(id);
        return ResponseEntity.ok(new DataDetailsDoctor(doctor));
    }



}
