package br.com.edumatt3

import com.google.protobuf.Timestamp
import io.grpc.ServerBuilder
import io.grpc.stub.StreamObserver
import java.time.LocalDateTime
import java.time.ZoneId

fun main() {

    val server = ServerBuilder.forPort(50051)
        .addService(EmployeeEndpoint()).build()

    server.start()
    server.awaitTermination()

}

class EmployeeEndpoint: EmployeeServiceGrpc.EmployeeServiceImplBase() {

    override fun register(request: EmployeeRequest?, responseObserver: StreamObserver<EmployeeResponse>?) {

        val name = request?.name
        val instant = LocalDateTime.now().atZone(ZoneId.of("UTC")).toInstant()
        val createdAt = Timestamp.newBuilder().setSeconds(instant.epochSecond).setNanos(instant.nano).build()
        val response = EmployeeResponse.newBuilder().setName(name).setCreatedAt(createdAt).build()

        responseObserver?.onNext(response)
        responseObserver?.onCompleted()
    }
}