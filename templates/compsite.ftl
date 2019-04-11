<?xml version='1.0' encoding='UTF-8' ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:cc="http://xmlns.jcp.org/jsf/composite"
      xmlns:f="http://xmlns.jcp.org/jsf/core"
      xmlns:p="http://primefaces.org/ui"
      xmlns:h="http://xmlns.jcp.org/jsf/html">

    <!-- INTERFACE -->
    <cc:interface>
        <cc:attribute name="object" type="${entity.javaClass.name}" required="true"/>
    </cc:interface>

    <!-- IMPLEMENTATION -->
    <cc:implementation>
            <p:panelGrid columns="2">
                
                <#assign jsf='#{'/>
            <#list entity.cols as col>
              
           <#if col.relation = 'ManyToOne'>
              <h:outputText value="${col.name}"/>
              <p:selectOneMenu value="${jsf}cc.attrs.object.${col.name}}">
                 <f:selectItem itemLabel="select" itemValue="${jsf}null}"/>
                 <f:selectItems value="${jsf}${mb}.${col.name}List}" var="a" itemLabel="${jsf}a}" itemValue="${jsf}a}"/>
             </p:selectOneMenu>
          
           <#elseif col.type = 'java.util.Date'>
                <h:outputText value="${col.name}"/>
                <p:calendar value="${jsf}cc.attrs.object.${col.name}}"/>
                
           <#elseif col.relation = 'None'>
                <h:outputText value="${col.name}"/>
                <p:inputText value="${jsf}cc.attrs.object.${col.name}}"/>
                
           </#if>              
            </#list>
               </p:panelGrid>
    </cc:implementation>
</html>