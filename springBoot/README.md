## eclipse 4.12
## spring tools sts plugin 4.3.1 
## SpringBoot 2.1.6



### 영속성 컨텍스트 1차 캐시

EntityManager persist()통해 Board.java 엔티티를 영속화시키면 

엔티티는 영속성 컨텍스트가 가지고있는 1차 캐시에 등록 
1차 캐시는 일종의 Map같은 컬랙션으로 Key @Id, Value 엔티티 객체 로 엔티티를 관리한다
1차 캐시에 저장된 엔티티는 바로 실제 데이터베이스에 반영되지 않는다
EntityTransaction으로 트랜잭션을 종료할 때 실제 데이터베이스에 반영된다

commit()호출하면  1차 캐시에 저장된 엔티티에 해당하는 insert 구문이 생성되고 데이터베이스로 전송
이렇게 영속성 컨텍스트에서 저장된 엔티티를 데이터베이스에 반영하는 과정을 플러시Flush라고 한다


### 영속성 컨텍스트 SQL 저장소

persist() 영속성 컨텍스트 1차개시 등록 
           1차 캐시에등록된 엔티티에 해당하는 insert 구문을 생성하여 SQL 저장소에 등록

등록 순서대로 1차 캐시와 SQL저장소에 누적된다
그리고 나서 commit() 으로 트랜잭션을 종료하면 SQL저장소에 저장되었던 SQL이 한꺼번에 데이터 베이스로 전송
이렇게 함으로써 한 번의 데이터베이스 통신으로 SQL 구문을 한꺼번에 처리할 수 있고 성능 최적화할 수 있다
마치 JDBC에서 배치 업데이트 같은 개념



### 스냅샷

영속성 컨텍스트에 저장할때 엔티티의 복사본을 만들어서 별도의 컬랙션에 저장하는데 이 저장 공간을 스냅샷이라고한다
그리고 트랜잭션이 종료될 때 스냅샷에 저장된 원래의 엔티티와 1차 캐시에 수정된 엔티티를 비교해서 변경된 값을 이용하여 update를 만드는 것이다

엔티티 수정에서 JPA기본전략은 모든 필드 수정이다
장점은 수정 쿼리가 항상 같기 때문에 애플리케이션 로딩 시점에 수정 쿼리를 미리 생성해 두고 재사용할수 있다는점이다
동일한 쿼리를 보내면 데이터베이스는 이전에 한 번 파싱했던 쿼리를 재사용 성능상 이점

