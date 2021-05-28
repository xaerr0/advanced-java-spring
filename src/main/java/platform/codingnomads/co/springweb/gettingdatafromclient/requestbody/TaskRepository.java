package platform.codingnomads.co.springweb.gettingdatafromclient.requestbody;


import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
