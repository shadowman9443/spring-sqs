package org.example.sqs.controller;




import lombok.RequiredArgsConstructor;
import org.example.sqs.controller.in.SqsMessageRequest;
import org.example.sqs.service.SqsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/sqs")
@RequiredArgsConstructor
public class SqsController {

    private final SqsService sqsService;

//    public SqsController(SqsService sqsService) {
//        this.sqsService = sqsService;
//    }

    @PostMapping(value = "/{queueName}")
    public ResponseEntity<?> createQueue(@PathVariable final String queueName){
        return ResponseEntity.ok(sqsService.createQueue(queueName));
    }

    @GetMapping()
    public ResponseEntity<?> listQueues(){
        return ResponseEntity.ok(sqsService.listQueues());
    }

    @PostMapping(value = "/messages")
    public ResponseEntity<?> createMessage(@RequestBody final SqsMessageRequest sqsMessageRequest){
        return ResponseEntity.ok(sqsService.publishMessage(sqsMessageRequest.getQueueUrl(), sqsMessageRequest.getMessage()));
    }

    @GetMapping(value = "/messages/{queueName}")
    public ResponseEntity<?> receiveMessage(@PathVariable final String queueName){
        return ResponseEntity.ok(sqsService.receiveMessages( "http://localhost:4566/000000000000/" + queueName));
    }

    @DeleteMapping(value = "/{queueName}")
    public ResponseEntity<?> deleteMessage(@PathVariable final String queueName){
        return ResponseEntity.ok(sqsService.removeQueue("http://localhost:4566/000000000000/" + queueName));
    }
}
