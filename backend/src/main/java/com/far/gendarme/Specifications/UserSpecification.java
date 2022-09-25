package com.far.gendarme.Specifications;
import com.far.gendarme.models.Diplome;
import com.far.gendarme.models.Formation;
import com.far.gendarme.models.User;
import com.far.gendarme.requests.FilterRequest;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class UserSpecification implements Specification<User> {

    private FilterRequest filter;

    public UserSpecification(FilterRequest filter) {
        super();
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb)  {
        Predicate p = cb.and();

        if (filter.getEmail() != null) p.getExpressions().add(cb.like(root.get("email"), "%"+filter.getEmail().getValue()+"%"));
        if (filter.getGrade() != null) p.getExpressions().add(cb.and(cb.equal(root.get("grade").get("name"), filter.getGrade().getValue())));
        if (filter.getAnciennete() != null) {
            if(filter.getAnciennete().getOperation().equals("is equal to")) p.getExpressions().add(cb.and(cb.equal(root.get("anciennete"), filter.getAnciennete().getValue())));
            else if(filter.getAnciennete().getOperation().equals("more then")) p.getExpressions().add(cb.and(cb.greaterThanOrEqualTo (root.get("anciennete"), filter.getAnciennete().getValue())));
            else if(filter.getAnciennete().getOperation().equals("less then")) p.getExpressions().add(cb.and(cb.lessThanOrEqualTo(root.get("anciennete"), filter.getAnciennete().getValue())));
        }
        if (filter.getAge() != null) {
            if(filter.getAge().getOperation().equals("is equal to")) p.getExpressions().add(cb.and(cb.equal(root.get("dateNaissance"), filter.getAge().getValue())));
            else if(filter.getAge().getOperation().equals("more then")) {
                try {
                    p.getExpressions().add(cb.and(cb.lessThanOrEqualTo (root.get("dateNaissance"), isMoreThenOrLessThen(filter.getAge().getValue()))));
                } catch (ParseException e) {}
            }
            else if(filter.getAge().getOperation().equals("less then")) {
                try {
                    p.getExpressions().add(cb.and(cb.greaterThanOrEqualTo(root.get("dateNaissance"), isMoreThenOrLessThen(filter.getAge().getValue()))));
                } catch (ParseException e) {}
            }
        }
        if (filter.getDiplome() != null) {
            Join<User , Diplome> join = root.join("diplomes",JoinType.INNER);
            p.getExpressions().add(cb.and(cb.equal(join.get("name"),filter.getDiplome().getValue())));
        }
        if (filter.getFormation() != null) {
            Join<User , Formation> join = root.join("formations",JoinType.INNER);
            p.getExpressions().add(cb.and(cb.equal(join.get("name"),filter.getFormation().getValue())));
        }
        return p;
    }

    public static Date isMoreThenOrLessThen(Object date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        LocalDate currentDate = LocalDate.now();
        int day = currentDate.getDayOfMonth();
        int month = currentDate.getMonthValue();
        int year = currentDate.getYear();
        return dateFormat.parse(day+"/"+month+"/"+(year -  Integer.parseInt(date.toString())));
    }
}
