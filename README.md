# java-egyptian-id-info-extractor
A minimal API to extract info from Egyptian ID number.
## To Run It
----
1. Install Maven 
2. cd into the project direcorty. then:
```bash
 ./mvnw spring-boot:run
```
OR
Run It Using Docker
```bash
 docker build -t info-extractor .
```
Then

```bash

 docker run -p 8090:8080 info-extractor

```

## API Endpoints 
----
 ### Extract Info From Id

   - Extracts all Info (Birthdate, Birth Governerate, Gender, Serial, Check Digit) from a given valid national ID.
* **URL**

  - [/api/extract-info-from-id/]()

* **Method:**

  `GET`
  
*  **URL Params**


   **Required:**
 
   requires URL query param: `?egyptianIDNumber=`
   
## Examples 
----
1. The following request:
```bash
curl http://localhost:8080/api/extract-info-from-id/?egyptianIDNumber=29808060103281
```
should result in the following response:
```bash
{
    "Birthdate": "August 6th 1998",
    "Birth Governorate": "Cairo",
    "Gender": "Female",
    "Serial": "0328",
    "Check Digit": "1"
}
```
## References
----
* [Wikipedia's Egyptian Natonal ID Number](https://ar.wikipedia.org/wiki/%D8%A8%D8%B7%D8%A7%D9%82%D8%A9_%D8%A7%D9%84%D8%B1%D9%82%D9%85_%D8%A7%D9%84%D9%82%D9%88%D9%85%D9%8A_%D8%A7%D9%84%D9%85%D8%B5%D8%B1%D9%8A%D8%A9)
* [NomgomFM's Article On Egyptian Natonal ID Number](https://www.nogoumfm.net/news/2019/04/%D8%A7%D9%84%D8%A3%D8%B1%D9%82%D8%A7%D9%85-%D8%A7%D9%84%D9%8014-%D8%B9%D9%84%D9%89-%D8%A8%D8%B7%D8%A7%D9%82%D8%A9-%D8%A7%D9%84%D8%B1%D9%82%D9%85-%D8%A7%D9%84%D9%82%D9%88%D9%85%D9%8A-%D9%87%D9%84/)
