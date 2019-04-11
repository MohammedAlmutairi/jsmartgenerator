<#assign jsf='#{'/>
<?xml version='1.0' encoding='UTF-8' ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:h="http://xmlns.jcp.org/jsf/html"
      xmlns:p="http://primefaces.org/ui"
      xmlns:f="http://xmlns.jcp.org/jsf/core"
      xmlns:form="http://xmlns.jcp.org/jsf/composite/form">
    <h:head>
        <title></title>
    </h:head>
    <h:body>
        <h:form>
        <p:messages/>  
            <form:${entity.simpleName2} 
                  object = "${jsf}${mb}.${object}}"
                
                />
        <p:commandLink value="Save" process="@form" update="@form" actionListener="${jsf}${mb}.save()}"/>         
        </h:form>
         
    </h:body>
</html>

