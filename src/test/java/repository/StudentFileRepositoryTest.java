package repository;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class StudentFileRepositoryTest {
    @org.junit.Test
    public void save() throws Exception {
    }

    @org.junit.Test
    public void findAll() throws Exception {
    }

    @org.junit.Test
    public void findOne() throws Exception {
    }

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(StudentFileRepository.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

}
