<?xml version='1.0' encoding='UTF-8' ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:h="http://xmlns.jcp.org/jsf/html"
      xmlns:p="http://primefaces.org/ui">
    <h:head>
        <title>Facelet Title</title>
    </h:head>
    <h:body>
        <p:panelGrid columns="2">
            <#list entities as entity> 
            <h:link value="${entity.javaClass.simpleName}" outcome="/${primefacesForm}/${entity.javaClass.simpleName}Form"/>
            </#list>
        </p:panelGrid>
    </h:body>
</html>
