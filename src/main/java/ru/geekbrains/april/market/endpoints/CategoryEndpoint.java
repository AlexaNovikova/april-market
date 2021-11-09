package ru.geekbrains.april.market.endpoints;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.geekbrains.april.market.services.CategoryServiceSoap;
import ru.geekbrains.april.market.soap.categories.GetCategoryByIdRequest;
import ru.geekbrains.april.market.soap.categories.GetCategoryByIdResponse;

@Endpoint
@RequiredArgsConstructor
public class CategoryEndpoint {
    private static final String NAMESPACE_URI = "http://www.geekbrains.ru/april/market/ws/categories";
    private final CategoryServiceSoap categoryServiceSoap;

    /*
        Пример запроса: POST http://localhost:8189/market/ws

        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
          xmlns:f="http://www.geekbrains.ru/april/market/ws/categories">
            <soapenv:Header/>
            <soapenv:Body>
                <f:getCategoryByIdRequest>
                    <f:title>1</f:title>
                </f:getCategoryByIdRequest>
            </soapenv:Body>
        </soapenv:Envelope>
     */

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCategoryByIdRequest")
    @ResponsePayload
    @Transactional
    public GetCategoryByIdResponse getCategoryByIdResponse(@RequestPayload GetCategoryByIdRequest request) {
        GetCategoryByIdResponse response = new GetCategoryByIdResponse();
        response.setCategory(categoryServiceSoap.getById(request.getId()));
        return response;
    }
}
