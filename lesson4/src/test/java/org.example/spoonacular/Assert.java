package org.example.spoonacular;

import net.javacrumbs.jsonunit.JsonAssert;
import net.javacrumbs.jsonunit.core.Option;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Assert {

    public void assertJson (Object expected,Object result){

        JsonAssert.assertJsonEquals(
                expected,
                result,
                JsonAssert.when(Option.IGNORING_ARRAY_ORDER));
    }


    public String getResourceAsString(String name) throws Exception {
        String resource = getClass().getSimpleName() + "/" + name;
        InputStream stream = getClass().getResourceAsStream(resource);
        assert stream != null;
        byte[] bytes = IOUtils.toByteArray(stream);
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public File getFile (String name){
        String resource = getClass().getSimpleName() + "/" + name;
        String file = getClass().getResource(resource).getFile();
        return new File(file);
    }
}
