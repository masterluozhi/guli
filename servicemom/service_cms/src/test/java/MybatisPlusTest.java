import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.jupiter.api.Test;

public class MybatisPlusTest {
    @Test
    public void run(){
        AutoGenerator au = new AutoGenerator();
        GlobalConfig gc = new GlobalConfig();
        String properties = System.getProperty("user.dir");
        gc.setOutputDir("F:\\workspace\\guliweb\\servicemom\\service_cms"+"/src/main/java");

        gc.setAuthor("test.java");
        gc.setOpen(false);
        gc.setFileOverride(false);
        gc.setServiceName("%Service");
        gc.setIdType(IdType.ID_WORKER_STR);
        gc.setDateType(DateType.ONLY_DATE);
        gc.setSwagger2(true);

        au.setGlobalConfig(gc);

        DataSourceConfig ds= new DataSourceConfig();
        ds.setUrl("jdbc:mysql://localhost:3306/guli?useUnicode=true&characterEncoding=UTF-8&userSSL=false&serverTimezone=GMT%2B8");
        ds.setDriverName("com.mysql.cj.jdbc.Driver");
        ds.setUsername("root");
        ds.setPassword("123456");
        ds.setDbType(DbType.MYSQL);

        au.setDataSource(ds);

        PackageConfig pc = new PackageConfig();
        pc.setModuleName("cmsservice");
        pc.setParent("com.kun");

        pc.setController("controller");
        pc.setEntity("entity");
        pc.setService("service");
        pc.setMapper("mapper");
        au.setPackageInfo(pc);

        StrategyConfig sc = new StrategyConfig();
        sc.setInclude("crm_banner");
        sc.setNaming(NamingStrategy.underline_to_camel);
        sc.setTablePrefix(pc.getModuleName()+"_");

        sc.setColumnNaming(NamingStrategy.underline_to_camel);
        sc.setEntityLombokModel(true);
        sc.setRestControllerStyle(true);
        sc.setControllerMappingHyphenStyle(true);
        au.setStrategy(sc);

        au.execute();
    }
}
