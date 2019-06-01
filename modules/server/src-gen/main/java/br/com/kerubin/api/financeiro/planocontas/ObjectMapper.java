/**********************************************************************************************
Code generated with MKL Plug-in version: 3.5.1
Code generated at time stamp: 2019-06-01T15:26:43.922
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.planocontas;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.Id;

import org.hibernate.Hibernate;
import org.hibernate.collection.spi.PersistentCollection;
import org.hibernate.proxy.HibernateProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component("financeiro.plano_contas.ObjectMapper")
public class ObjectMapper {
	
	private static Logger log = LoggerFactory.getLogger(ObjectMapper.class);
	
	private static final List<?> DSL_PRIMITIVE_TYPES = Arrays.asList(LocalDate.class, LocalTime.class, LocalDateTime.class,
			Date.class, Instant.class, String.class, Long.class, 
            Boolean.class, UUID.class, BigDecimal.class, Double.class);
	
	
	public <T> T map(Object source, Class<T> targetClass) {
        if (source == null) {
            return null;
        }
        
        T target = BeanUtils.instantiateClass(targetClass);
        copyProperties(source, target);
        return target;
    }
	
	public void copyProperties(Object source, Object target) {
        Map<Object, Object> visited = new HashMap<>();
        copyProperties(source, target, visited);
    }
    
    protected void copyProperties(Object source, Object target, Map<Object, Object> visited) {
        if (source == null || target == null) {
            return;
        }
        
        visited.put(source, target);
        
        List<Field> sourceFieds = Arrays.asList(source.getClass().getDeclaredFields());
        List<Field> targetFieds = Arrays.asList(target.getClass().getDeclaredFields());
        targetFieds.forEach(targetField -> {
            Optional<Field> sourceFieldOptional = sourceFieds.stream().filter(it -> it.getName().equals(targetField.getName())).findFirst();
            if (sourceFieldOptional.isPresent()) {
                Field sourceField = sourceFieldOptional.get();
                //targetField.setAccessible(true);
                //sourceField.setAccessible(true);
                try {
                    Class<?> targetFieldType = targetField.getType();
                    Class<?> sourceFieldType = sourceField.getType();
                    
                    /*if (! targetFieldType.getSimpleName().equals(sourceFieldType.getSimpleName())) {
                    	throw new IllegalStateException("Target field '" + targetField.getName() + "' type '" + targetFieldType + "' does not match with source filed '" + sourceField.getName() + "' type '" + sourceFieldType + "'.");
                    }*/
                    
                    if (DSL_PRIMITIVE_TYPES.stream().anyMatch(it -> it.equals(targetFieldType)) || (targetFieldType.isPrimitive() && sourceFieldType.isPrimitive()) ) {
                    	Object value = getFieldValue(source, sourceField);
                    	setFieldValue(target, targetField, value);
                    }
                    else if (targetFieldType.isEnum()) {
                        if (sourceFieldType.isEnum()) {
                        	Object value = getFieldValue(source, sourceField);
                            if (value != null) {
	                            String sourceEnumName = ((Enum<?>) value).name();
	                            
	                            @SuppressWarnings({ "rawtypes", "unchecked" })
	                            Enum<?> targetEnumValue = Enum.valueOf( (Class<Enum>) targetFieldType, sourceEnumName);
	                            setFieldValue(target, targetField, targetEnumValue);
                            }
                        }
                        else {
                        	throw new IllegalStateException("Source \"" + sourceFieldType.getName() + "\" is not a enum.");
                        }
                    }
                    else { // Is another entity reference, call recursive if needed.
						Object sourceFieldValue = getFieldValue(source, sourceField);
						if (isProxy(sourceFieldValue)) {
							sourceFieldValue = Hibernate.unproxy(sourceFieldValue);
						}
						
					    if (sourceFieldValue != null) {
					        Object targetFieldValue = null;
					        if (visited.containsKey(sourceFieldValue)) {
					            targetFieldValue = visited.get(sourceFieldValue);
					        }
					        else {
					        	
					            targetFieldValue = BeanUtils.instantiateClass(targetFieldType);
					            
					            // Source field can has an entity name, but with uuid value instead of an entity object
					            // Also, source field and target field can be objects with field names matching, but with different class names.
					            boolean isSourceFieldAnEntityWithOnlyId = sourceFieldType.equals(UUID.class) && !targetFieldType.equals(UUID.class);
					            
					            if (!isSourceFieldAnEntityWithOnlyId) { // both should be objets
					            	copyProperties(sourceFieldValue, targetFieldValue, visited);
					            }
					            else {
				            		Field targetFieldValueIdField = getEntityIdField(targetFieldValue.getClass());
				            		if (targetFieldValueIdField == null) {
				            			throw new IllegalStateException(targetField.getClass().getName() + " should be an entity class with a field annoted with @Id.");
				            		}
				            		setFieldValue(targetFieldValue, targetFieldValueIdField, sourceFieldValue);
					            }
					            
					        }
					        
					        setFieldValue(target, targetField, targetFieldValue);
					    }
					}
                } catch (Exception e) {
                	throw new IllegalStateException("Error copying properties from '" + source.getClass().getName() + "' to '" + target.getClass().getName() + "'.", e);
                }
            }
            else {
            	//throw new IllegalStateException("Target field \"" + target.getClass().getName() + "." + targetField.getName() + "\" not found in source \"" + source.getClass().getName() + "\".");
            }
        });
       
    }
    
    private boolean isProxy(Object value) {
        if (value == null) {
            return false;
        }
        if ((value instanceof HibernateProxy) || (value instanceof PersistentCollection)) {
            return true;
        }
        return false;
    }
    
    private Field getEntityIdField(Class<?> entityClass) {
		Field idField = Arrays.asList(entityClass.getDeclaredFields())
				.stream()
				.filter(field -> field.isAnnotationPresent(Id.class))
				.findFirst()
				.orElse(null);
		
		return idField;
	}

    
    private void setFieldValue(Object obj, Field field, Object value) {
    	PropertyDescriptor pd = getPropertyDescriptor(obj, field);
    	if (pd != null && pd.getWriteMethod() != null) { // Has setter
    		try {
				pd.getWriteMethod().invoke(obj, value);
			} catch (Exception e) {
				log.error("Erro setting object value: " + e.getMessage(), e);
			}
    	}  else { // Does not has setter.
    		try {
    			field.setAccessible(true);
    			field.set(obj, value);
			}
			catch(Exception ex) {
				log.error("Erro getting object value: " + ex.getMessage(), ex);
			}
    	}
    }
    
    private Object getFieldValue(Object obj, Field field) {
    	PropertyDescriptor pd = getPropertyDescriptor(obj, field);
    	Object value = null;
    	if (pd != null && pd.getReadMethod() != null) {
    		try {
				value = pd.getReadMethod().invoke(obj); // Has getter method
			} catch (Exception e) {
				log.error("Erro getting object value: " + e.getMessage(), e);
			}
    	}  else { // Does not has getter.
    		try {
    			field.setAccessible(true);
				value = field.get(obj);
			}
			catch(Exception ex) {
				log.error("Erro getting object value: " + ex.getMessage(), ex);
			}
    	}
    	
    	return value;
    }
    
    private PropertyDescriptor getPropertyDescriptor(Object obj, Field field) {
    	PropertyDescriptor pd = null;
    	try {
    		pd = BeanUtils.getPropertyDescriptor(obj.getClass(), field.getName());
    	} catch(Exception e) {
    		log.error("Erro getting property descriptor: " + e.getMessage(), e);
    	}
    	return pd;
    }


}

