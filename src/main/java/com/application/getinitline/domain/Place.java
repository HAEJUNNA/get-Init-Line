package com.application.getinitline.domain;

import com.application.getinitline.constant.PlaceType;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
/*
*  entity가 동일하다는 의미?
*  일반적인 데이터 오브젝트랑 달리 entity는 "모든 필드가 다 동일하다는걸 검사를 해야 동등하다" 이렇게 평가할 필요가 없다.
*  persistence context가 관리를 하는 영속성 관리를 해주는 entity는 제대로 등록이 됐다면,
*  id기준으로 등록이 된다. 즉 id가 같으면 같은 객체인것이다.
*  그렇기 때문에 entity경우는 dto와 다르게, 기준이 id이기 떄ㅜㅁㄴ에 equals 작성시, id만 구현을 하면된다.
* 엔티티의 동등성 비교를 편하게 하려고 롬복을 도입했는데
* 엔티티는 자바의 데이터 클래스랑은 다른 특성을 이용해
* 이 동등성 기준을 각 필드 전체 비교에서 `id` 비교로 하고자 함
* 즉 `a(id=1)`, `b(id=1)` 일 때, `a.equals(b) == True`
* 그러나 id는 persistence context가 관리하므로
* 서비스 코드에서 처음 만들 땐 `null` 인데,
* 롬복이나 ide 자동 작성 코드는 이 부분을 `true`로 처리함
* 즉 `a(id=null)`, `b(id=null)` 일 때, `a.equals(b) == True`
* 그냥 보면 맞는 것 같지만 실은 둘이 단지 아직 `id`를
* 부여받지 못했을 뿐, 다른 데이터를 가진 서로 다른 엔티티일 수 있기 때문에, `equals()` 직접 구현이 필요.
* 한편 `hashCode()`도 구현해줘야 하는데 동등성 기준을 `id`로 잡는다면 `hashCode()` 기준값도
* `id`를 이용해 다양한 수를 만들면 좋지만 위와 같은 이유로 영속화가 안된 엔티티는 아직 `id`가 없으므로
* 그냥 상수를 넣든지, 다른 방법이 필요함 상수를 넣어도 큰 무리 없지만 좀 더 효율적으로 만들기 위해
* 각 엔티티 인덱스 컬럼을 활용해 `hash()` 생성
*
* */
// 하지만 set같은 자료구조에 넣을수없기때문에, entity경우 EqualsAndHashCode 자체를 만들지 않는다.
//@EqualsAndHashCode(onlyExplicitlyIncluded = true) // true인경우 내가 equals를 직접 구체적으로 명시한것만 넣게다는 뜻이다.
@Getter
@ToString
@Table(indexes = {
    @Index(columnList = "placeName"),
    @Index(columnList = "address"),
    @Index(columnList = "phoneNumber"),
    @Index(columnList = "createdAt"),
    @Index(columnList = "modifiedAt")
})
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Place {

//    @EqualsAndHashCode.Include // 해당 어노테이션이 붙은 컬럼만 equals를 반영하겠다는 의미이다.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Setter
    @Column(nullable = false, columnDefinition = "varchar(20) default 'COMMON'")
    @Enumerated(EnumType.STRING)
    private PlaceType placeType;

    @Setter
    @Column(nullable = false)
    private String placeName;

    @Setter
    @Column(nullable = false)
    private String address;

    @Setter
    @Column(nullable = false)
    private String phoneNumber;

    @Setter
    @Column(nullable = false, columnDefinition = "integer default 0")
    private Integer capacity;


    @Setter
    private String memo;


    @Column(nullable = false, insertable = false, updatable = false,
        columnDefinition = "datetime default CURRENT_TIMESTAMP")
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(nullable = false, insertable = false, updatable = false,
        columnDefinition = "datetime default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
    @LastModifiedDate
    private LocalDateTime modifiedAt;


    @ToString.Exclude // 순환참조 방지를 위한 설정
    @OrderBy("id")
    @OneToMany(mappedBy = "place")
    private final Set<Event> events = new LinkedHashSet<>();

    @ToString.Exclude // 순환참조 방지를 위한 설정
    @OrderBy("id")
    @OneToMany(mappedBy = "place", cascade = CascadeType.REMOVE)
    private final Set<AdminPlaceMap> adminPlaceMaps = new LinkedHashSet<>(); //set을 이용해 중복을 피할수있고, 순서 유지를 위해 LinkedHashSet 사용


    protected Place() {
    }

    protected Place(
        PlaceType placeType,
        String placeName,
        String address,
        String phoneNumber,
        Integer capacity,
        String memo
    ) {
        this.placeType = placeType;
        this.placeName = placeName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.capacity = capacity;
        this.memo = memo;
    }

    public static Place of(
        PlaceType placeType,
        String placeName,
        String address,
        String phoneNumber,
        Integer capacity,
        String memo
    ) {
        return new Place(placeType, placeName, address, phoneNumber, capacity, memo);
    }

    // 직접구현
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        return id != null && id.equals(((Place) obj).getId());
    }
    // 직접구현
    @Override
    public int hashCode() {
        return Objects.hash(placeName, address, phoneNumber, createdAt, modifiedAt);
    }

}
