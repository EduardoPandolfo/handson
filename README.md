Aplicação JSF com JoinFaces, Primefaces, Spring com login em memória fixo.

Configuração para execução da API na Amazon AWS - Modelo EC2

## Iniciar uma instância quickstart do Ubuntu

- Utilizando qualquer imagem de máquina recomendada.
- Tipo de instância t2.micro.
- Criar configuração TCP customizada para o intervalo de portas 8080, aceitando conexões de qualquer IP.

Para fazer a conexão através de SSH da instância criada, execute o seguinte comando (certifique-se de executá-lo na pasta onde foi feito o download da chave privada):

Iniciar uma instância quickstart do ubuntu
- utilizando qualquer imagem de maquina recomendada
- tipo de instância t2.micro
- criar configuração tcp customizada para o intervalo de portas 8080, aceitando conexões de qualquer ip 
- conexão feita através de ssh da instância criada 
  * obs: comando executado na pasta que foi feito o download da chave privada
    

    $  ssh -i "aw-spring-api-amazon-aws.pem" ec2-user@ec2-3-138-174-198.us-east-2.compute.amazonaws.com
	 
	
- estando no servidor ec2, instalar dependências


    $ sudo apt-get update
	$ sudo apt install default-jre
  
- clonar o projeto publico do git
	

	$ git clone https://github.com/EduardoPandolfo/handson.git

	
- baixar o mysql server
	

	$ sudo apt-get install mysql-server

- após instalar, acessar como root
	

	$ sudo mysql -u root

	
- criar uma senha pro usuário root, criar usuario e o banco de dados
    * não será necessário configurar variaveis de ambiente
	

	$ ALTER USER 'root'@'localhost' IDENTIFIED BY 'SENHA&1212';
	$ CREATE USER 'user'@'127.0.0.1' IDENTIFIED WITH mysql_native_password BY 'user123';
	$ GRANT ALL PRIVILEGES ON *.* TO 'user'@'127.0.0.1';
	$ flush privileges;
	$ create database aerea_db;
	$ exit
	
	
- depois de finalizar com o mysql, entrar na pasta clonada do git e rodar o maven
	

	$ mvn clean package
	
- se aparecer mvn 'command not found' rodar 
  

    $ sudo apt-get install maven

	
- entrar na pasta target e executar o jar via terminal 
	

	$ cd target/	
	$ java -jar handson-0.0.1-SNAPSHOT.jar


#URL para acesso: http://18.119.29.61:8080/
	
## logins válidos (usuario - senha ) 

    admin - admin 
	gestor1 - gestor1
	gestor2 - gestor2
						
 - se por algum acaso for redirecionado a alguma página do faces, pode acessar diretamente as telas pelos links
   

	url home: http://18.119.29.61:8080/inicio.xhtml
	url voo: http://18.119.29.61:8080/voo.xhtml
	url aeroporto:http://18.119.29.61:8080/inicio.xhtml