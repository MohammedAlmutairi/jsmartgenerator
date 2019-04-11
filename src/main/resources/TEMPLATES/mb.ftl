/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ${manageBeanPackage};
import javax.ejb.EJBException;
import  ${facadePackage}.${entity.javaClass.simpleName}Facade;
import ${entity.javaClass.name};
    <#list entity.cols as col>
    <#if col.relation = 'ManyToOne'>
import ${facadePackage}.${col.simpleType}Facade;
import ${entityPackage}.${col.simpleType};
    </#if>
    </#list>   
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
           <#assign jsf='#{'/>
/**
 *
 * @author JSmartGenerator Tools
 */
@Named(value = "${entity.manageBean}")
@ViewScoped
public class ${entity.javaClass.simpleName}MB  implements Serializable   {

 
    ${entity.javaClass.simpleName} ${entity.objectName} = new ${entity.javaClass.simpleName}();
    @EJB ${entity.javaClass.simpleName}Facade ${entity.objectName}Facade;
    <#list entity.cols as col>
    <#if col.relation = 'ManyToOne'>
     @EJB ${col.simpleType}Facade ${col.name}Facade;
    public List<${col.simpleType}> get${col.name2}List()
    {
        return ${col.name}Facade.findAll();
    }
    </#if>
    </#list>
    
    

    
    public ${entity.javaClass.simpleName}MB() {
    }

    public ${entity.javaClass.simpleName}  get${entity.javaClass.simpleName}() {
        return ${entity.objectName};
    }

    public void setUsers(${entity.javaClass.simpleName} abc) {
        this.${entity.objectName} = abc;
    }
     public void addMsg(String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(msg));
    }
   public void save() {
        try {
            if (${entity.objectName}.get${entity.pk.name2}() == null) {
                ${entity.objectName}Facade.create(${entity.objectName});
               ${entity.objectName} = new ${entity.javaClass.simpleName}();
            } else {
                 ${entity.objectName}Facade.edit(${entity.objectName});
            }
            addMsg("Saved is OK");
        } catch (EJBException exception) {
            addMsg("Problem in Save");
        }

    }
}
