# RESTful API for CEP Data Retrieval

This project is a RESTful API built in Java that provides an endpoint to fetch data based on a given CEP (Brazilian postal code). The API will retrieve the following information:

- Logradouro (Street address)
- Bairro (Neighborhood)
- Cidade (City)
- Estado (State)
- País (Country)
- Código IBGE da cidade (City IBGE code, if available)
- Código IBGE do estado (State IBGE code, if available)

## Endpoint Details

### GET /api/cep/{cep}

This endpoint accepts a CEP (Brazilian postal code) as a parameter and fetches the data from the first API that responds:

1. ViaCEP API: [https://viacep.com.br/](https://viacep.com.br/)
2. ApiCEP API: [https://apicep.com/api-de-consulta/](https://apicep.com/api-de-consulta/)
3. BrasilAPI: [https://brasilapi.com.br/docs#tag/CEP](https://brasilapi.com.br/docs#tag/CEP)

The API will return the data from the first successful response.

## Usage

To use the API, send a GET request to the endpoint `/api/cep/{cep}` with the desired CEP in the path parameter.

Example Request:

```
GET /api/cep/01001-000
```

Example Response:

```json
{
  "logradouro": "Praça da Sé",
  "bairro": "Sé",
  "cidade": "São Paulo",
  "estado": "SP",
  "país": "Brasil",
  "codigoIBGECidade": null,
  "codigoIBGEEstado": null
}
```

# API Documentation

The API documentation is available at the following URL when the application is running:

http://localhost:8080/swagger-ui.html

You can use Swagger UI to explore the available endpoints, request/response formats, and also test the API directly from the web interface.

## Contributing

Feel free to contribute to this project by submitting pull requests or reporting issues. Your contributions are greatly appreciated!

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
