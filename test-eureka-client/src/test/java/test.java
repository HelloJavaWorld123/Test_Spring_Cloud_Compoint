import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/2/10  10:17
 * Version: V1.0
 * Description:
 * ======================
 */
@PropertySource(value = "classpath:application.yml")
public class test {

    @Value(value = "${spring.datasource.url}")
    private String url ;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Test
    public void geturl(){

        System.out.println(url);

    }
}
