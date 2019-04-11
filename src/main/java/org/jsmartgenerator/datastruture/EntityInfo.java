/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jsmartgenerator.datastruture;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import static java.util.stream.Collectors.toList;
 
 

/**
 *
 * @author Dell
 */
public class EntityInfo {
    
    private Class javaClass;
    private Field fieldName;
    private Annotation annotationName;
    private PrimaryKeyInfo pk;
    private List<PrimaryKeyInfo> embeddedId;
    private String className;
    
    private List<Column> cols;

 

    public Field getFieldName() {
        return fieldName;
    }

    public void setFieldName(Field fieldName) {
        this.fieldName = fieldName;
    }

    public Annotation getAnnotationName() {
        return annotationName;
    }

    public void setAnnotationName(Annotation annotationName) {
        this.annotationName = annotationName;
        

    }
    
    
    public String getSimpleName2()
    {
        String simple = getJavaClass().getSimpleName();
        String ret = simple.substring(0, 1).toLowerCase();
        if (simple.length() > 1)
        {
            ret = ret + simple.substring(1);
        }
        
        return ret;
    }

    public PrimaryKeyInfo getPk() {
        return pk;
    }

    public void setPk(PrimaryKeyInfo pk) {
        this.pk = pk;
    }

    public List<PrimaryKeyInfo> getEmbeddedId() {
        return embeddedId;
    }

    public void setEmbeddedId(List<PrimaryKeyInfo> embeddedId) {
        this.embeddedId = embeddedId;
    }

 
    
    public void findePK()
    {
        if (fieldName != null)
        {
            pk = new PrimaryKeyInfo();
            pk.setName(fieldName.getName());
            pk.setType(fieldName.getType().getName());
        }
        
    }
    
    
    
    public void findEmbeddedId(Class className)
    {
        embeddedId = Arrays.stream(className.getDeclaredFields())
                .map(field -> {
                    PrimaryKeyInfo pk = new PrimaryKeyInfo();
                    pk.setName(field.getName());
                    pk.setType(field.getType().getName());
                    return pk;
                })
                .collect(toList())
                ;
               
     }

    public Class getJavaClass() {
        return javaClass;
    }

    public void setJavaClass(Class javaClass) {
        this.javaClass = javaClass;
        findClassName();
        
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    private void findClassName() { 
        setClassName(javaClass.getSimpleName());
        
    }

    public List<Column> getCols() {
        return cols;
    }

    public void setCols(List<Column> cols) {
        this.cols = cols;
    }
    
    public String getManageBean()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(getJavaClass().getSimpleName().substring(0,1).toLowerCase());
        sb.append(getJavaClass().getSimpleName().substring(1));
        sb.append("MB");
        return sb.toString();
    }
    
    public String getObjectName()
    {
        StringBuilder sb = new StringBuilder();
//        sb.append(getMB());
//        sb.append(".");
        sb.append(getJavaClass().getSimpleName().substring(0, 1).toLowerCase());
        sb.append(getJavaClass().getSimpleName().substring(1));
        return sb.toString();
    }
    
    
}
