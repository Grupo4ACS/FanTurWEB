# FanTurWEB
## Instrucciones
1. Clonar el repositorio e importarlo al IDE.
2. Descargar el conector JDBC para MySQL desde https://dev.mysql.com/downloads/file/?id=476198
3. Descomprimirlo en $(JBOSS)/modules/system/layers/base/com/mysql/main/
4. Editar el archivo $(JBOSS)/configuration/standalone.xml:
```
<datasource jndi-name="java:jboss/datasources/FanturDS" pool-name="FanturDS" enabled="true" use-java-context="true">
	<connection-url>jdbc:mysql://localhost/fantur?useSSL=false</connection-url>
	<driver>mysql</driver>
	<security>
		<user-name>fantur</user-name>	
		<password>fantur</password>			
	</security>
</datasource>
```
5. En el mismo archivo, agregar:
```
<driver name="mysql" module="com.mysql">
	<xa-datasource-class>com.mysql.jdbc.jdbc2.optional.MysqlXADataSource</xa-datasource-class>
</driver>
```
6. Agregar el proyecto al servidor Wildfly.
7. Ejecutar el servidor.

8. Pegar las siguientes líneas en la sección security-domains del standalone:

                <security-domain name="school" cache-type="default">
                    <authentication>
                        <login-module code="Database" flag="required">
                            <module-option name="dsJndiName" value="java:/jboss/datasources/FanTurDS"/>
                            <module-option name="rolesQuery" value="SELECT rol AS 'Role', 'Roles' FROM fantur_db.user u WHERE u.user = ?"/>
                            <module-option name="principalsQuery" value="SELECT password AS 'Password' FROM fantur_db.user u WHERE u.user = ?"/>
                        </login-module>
                    </authentication>
                </security-domain>
