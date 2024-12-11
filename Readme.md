
## Spring-Boot-Rest-API
<div >
Ce document résume les rootes utilisable avec cette application Spring Boot.
</div>
<p id="readme-top">Tout d'abort, il faut pull ou clone sur la banch <ins>developement</ins>.
Pour la configuration de la base de donnée, c'est dans application.properties
</p>

>git pull origin devlpoement


# Outils
*JDK 22+*

L'IDE,  on peut choisi de travailler avec Intellij IDEA et Eclipse

*Maven*


<br><br>
***Exemple :*** 

ulr : `api/user/login`

Json :
```json lines 
{
  op: "unue",
  bi: 4
}
```

<br><br>
<br><br>
***requette sans authentification :***

*-inscription* 
`api/user/register`

````json lines
{
  "user" :
  {
    "lastName": "trapp",
    "firstName": "manuel",
    "email" : "laupawy@gmacvil.com",
    "password": "111",
    "gender" : "Homme",
    "country" : "France",
    "phoneNumber":"12002",
    "languages" : ["French"],
  }
}
````
<br>

*-verif_email*
`api/user/verify`

````json lines
{
  email: "exemple@gmail.com",
  code: "132"
}
````
<br>

*-connection*
`api/user/login`

````json lines
{
  email: "exemple@gmail.com",
  password: "15132"
}
````    
<br>

*-Forgot_password*
`api/user/forgot-password`

````json lines
{
  email: "exemple@gmail.com",
}
````
<br>

*-Reset_password*
`api/user/reset-password`

````json lines
{
  token: "1g0sr3g21srg20rse",
  newPassword: "15132"
}
````
<br>

*-Address*
`api/add/adress`

[ with and without authentication ]

````json lines

{
  firstName:"james",
  lastName:"camerone",
  streetName:"15 rue du country",
  streetNameOptional: "",
  postCode:"75000",
  city:"Paris",
  phoneNumber:"+33678970",
  country:"France",
  "image":"img"
}
````





<p align="right">(<a href="#readme-top">back to top</a>)</p>
