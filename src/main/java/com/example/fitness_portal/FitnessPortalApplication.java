package com.example.fitness_portal;

import com.example.fitness_portal.core.entities.AppUser;
import com.example.fitness_portal.core.entities.Article;
import com.example.fitness_portal.infra.api.services.UserService;
import com.example.fitness_portal.infra.jpa.JpaArticleRepository;
import com.example.fitness_portal.infra.jpa.JpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;


@SpringBootApplication
public class FitnessPortalApplication {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;

    @Autowired
    private JpaArticleRepository articleRepository;

    public static void main(String[] args) {
        SpringApplication.run(FitnessPortalApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
//        AppUser appUser = new AppUser("kajapati", passwordEncoder.encode( "kaja1238"), new Date(), false);
//        repository.save(appUser);
        Article article = new Article();
        article.setTitle("Zasady zdrowej diety - Jak dbać o swoje zdrowie poprzez właściwe odżywianie");
        article.setAuthor(userService.findUserByUserName("kajapati3").get());
        article.setArticleText("Wprowadzenie:\n" +
                "Zdrowa dieta odgrywa kluczową rolę w naszym ogólnym zdrowiu i samopoczuciu. Regularne spożywanie zrównoważonych posiłków, bogatych w niezbędne składniki odżywcze, może dostarczyć organizmowi energii, poprawić funkcje układu immunologicznego i pomóc w utrzymaniu zdrowej wagi. W tym artykule przedstawimy kilka podstawowych zasad zdrowej diety, które mogą być pomocne w codziennym dbaniu o swoje zdrowie.\n" +
                "\n" +
                "Jedz różnorodne produkty spożywcze:\n" +
                "Zróżnicowanie jest kluczowe, gdy chodzi o zdrową dietę. Staraj się spożywać różne grupy produktów spożywczych, takie jak warzywa, owoce, pełnoziarniste produkty zbożowe, białko roślinne i zwierzęce, zdrowe tłuszcze, produkty mleczne o niskiej zawartości tłuszczu itp. Każda grupa produktów dostarcza unikalne składniki odżywcze, dlatego ważne jest, aby wprowadzać różnorodność do swojej diety.\n" +
                "\n" +
                "Unikaj przetworzonej żywności:\n" +
                "Przetworzona żywność, taka jak fast foody, słodycze, napoje gazowane i gotowe dania, często zawiera dużo dodatków chemicznych, konserwantów, soli i cukrów. Staraj się ograniczyć spożycie takiej żywności i zamiast niej wybieraj świeże, naturalne produkty. Gotowanie w domu to świetny sposób na kontrolowanie jakości swojej diety i unikanie niezdrowych składników.\n" +
                "\n" +
                "Ogranicz spożycie soli i cukru:\n" +
                "Zbyt duża ilość soli i cukru w diecie może prowadzić do różnych problemów zdrowotnych. Ogranicz spożycie soli, unikaj solenia potraw i staraj się czytać etykiety produktów spożywczych, aby wybierać te o niskiej zawartości sodu. Podobnie, ogranicz spożycie cukru dodawanego, takiego jak cukier rafinowany i słodkie napoje. Wybieraj naturalnie słodkie produkty, takie jak owoce, aby zaspokoić swoje łaknienie na słodycze.\n" +
                "\n" +
                "Pij odpowiednią ilość wody:\n" +
                "Picie odpowiedniej ilości wody jest niezwykle ważne dla utrzymania prawidłowego funkcjonowania organizmu. Woda pomaga w nawodnieniu, usuwaniu toksyn, utrzymaniu prawidłowej temperatury ciała i zapewnieniu odpowiedniej kondycji skóry.");
        article.setCategory("diet");
        article.setPictureLink("https://img.freepik.com/free-vector/healthy-vs-unhealthy-food-flat-illustration_74855-18384.jpg?w=1380&t=st=1685295198~exp=1685295798~hmac=bda614a5ab0b46eb7407d4617bee4ec1fc3959e2f0e579efd0c8a8e38d457af6");
        articleRepository.save(article);
    }
}
