package fr.damienraymond.servicejava.tp2.serverRegister.infrastructure.jaxrs.annotations;

import javax.ws.rs.NameBinding;
import java.lang.annotation.*;

@NameBinding
@Target( {ElementType.TYPE, ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
@Inherited
public @interface VersionReponses {

}
