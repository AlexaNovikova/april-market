package ru.geekbrains.april.market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@PropertySource("classpath:app.properties")
public class AprilMarketApplication {


	//добавить сущность заказ! сделать привязку с OrderItems
	//добавить подвязку к пользователю
	//План:
	//1. Добавить фото
	//2. Где хранить корзину? Надо сделать для каждого юзера свою корзину
	//3. Склад
	//4. Добавить платежную систему
	//5. Загрузка товаров из файла
	//6. Регистрация пользователя
	//7. Фильтр товаров
	//8. Рубрикатор товаров
	//9. Комментарии клиентов к товарам(возможно рейтинг)
	//10. При оформлении заказа нужно указать доп. информацию: телефон, адрес, контактное лицо
	//11. Промокод

	//. *** Интеграция с 1С
	//. *** Админка


    // ДЗ 10 пункт
	// пункт 6 через JSON
	// **  5 пункт


	public static void main(String[] args) {
		SpringApplication.run(AprilMarketApplication.class, args);
	}

}
