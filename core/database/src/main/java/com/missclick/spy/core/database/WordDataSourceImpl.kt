package com.missclick.spy.core.database

import com.missclick.spy.core.database.enity.asEntity
import com.missclick.spy.core.database.enity.asModel
import com.missclick.spy.core.database.room.SpyDatabase
import com.missclick.spy.core.model.Collection
import com.missclick.spy.core.model.Language
import com.missclick.spy.core.model.Word
import kotlinx.coroutines.flow.Flow

internal class WordDataSourceImpl(
    private val db: SpyDatabase,
) : WordDataSource {

    override fun getCollections(languageCode: String): Flow<List<String>> {
        return db.wordDao().getCollections(languageCode)
    }

    override suspend fun getLanguages(): List<Language> {
        return db.wordDao().getLanguages().map { it.asModel() }
    }

    override suspend fun getCollection(collectionName: String, languageCode: String): Collection {
        return db.wordDao().getCollection(collectionName, languageCode).asModel()
    }

    override suspend fun getDefaultCollection(languageCode: String): String {
        return db.wordDao().getDefaultCollection(languageCode)
    }

    override fun getWords(collection: String, languageCode: String): Flow<List<String>> {
        return db.wordDao().getWords(collectionName = collection, languageCode)
    }

    override suspend fun deleteCollection(collectionName: String, languageCode: String) {
        db.wordDao().deleteCollection(collectionName, languageCode)
    }

    override suspend fun deleteWord(wordName: String) {
        db.wordDao().deleteWord(wordName)
    }

    override suspend fun addWord(
        word: Word,
        collectionName: String,
        languageCode: String,
    ) {
        val collectionEntity = db.wordDao().getCollection(collectionName, languageCode)
        val wordEntity = word.asEntity(collectionEntity.id)
        db.wordDao().insertWord(wordEntity)
    }

    override suspend fun addCollection(collection: Collection, languageCode: String) {
        val languageEntity = db.wordDao().getLanguage(languageCode)
        val collectionEntity = collection.asEntity(languageEntity.id)
        db.wordDao().insertCollection(collectionEntity)
    }

    override suspend fun checkIsExistLanguage(languageCode: String): Boolean {
        return db.wordDao().isExistLanguage(languageCode)
    }

    override suspend fun getDefaultLanguage(): String {
        return db.wordDao().getDefaultLanguage()
    }

    override suspend fun getCollectionLanguage(collectionName: String): String {
        return db.wordDao().getCollectionLanguage(collectionName)
    }

    override suspend fun addWordsForDev() {
        val wordsRuBasic = listOf(
            "Квартира",
            "Cупермаркет",
            "Рынок",
            "Бункер",
            "Свалка",
            "Гараж",
            "Дача",
            "Церковь",
            "Лифт",
            "Эскалатор",
            "Парк",
            "Казино",
            "Банк",
            "Школа",
            "Детский сад",
            "Отель",
            "АЗС (Заправка)",
            "Бар",
            "Ресторан",
            "Пляж",
            "Пустыня",
            "Посольство",
            "Аптека",
            "Библиотека",
            "Бассейн",
            "Тюрьма",
            "Аэропорт",
            "Вокзал",
            "Станция метро",
            "Зоопарк",
            "Кинотеатр",
            "Театр",
            "Музей",
            "Склад",
            "Салон красоты",
            "Спортивный зал",
            "Стадион",
            "Кладбище",
            "Ферма",
            "Больница",
            "Университет",
            "Парикмахерская",
            "Гостиница",
            "Офис",
            "Автобусная остановка",
            "Торговый центр",
            "Лагерь",
            "Порт",
            "Пекарня",
            "Автосервис",
            "Почта",
            "Концертный зал",
            "Картинг",
            "Военная база",
            "Шахта",
            "Конюшня",
            "Цирк",
            "Галерея",
            "Боулинг",
        )
        val wordsUkBasic = listOf(
            "Квартира",
            "Cупермаркет",
            "Ринок",
            "Бункер",
            "Звалище",
            "Гараж",
            "Дача",
            "Церква",
            "Ліфт",
            "Ескалатор",
            "Парк",
            "Казино",
            "Банк",
            "Школа",
            "Дитячий садок",
            "Готель",
            "АЗС (Заправка)",
            "Бар",
            "Ресторан",
            "Пляж",
            "Пустеля",
            "Посольство",
            "Аптека",
            "Бібліотека",
            "Басейн",
            "В'язниця",
            "Аеропорт",
            "Вокзал",
            "Станція метро",
            "Зоопарк",
            "Кінотеатр",
            "Театр",
            "Музей",
            "Склад",
            "Салон краси",
            "Спортивна зала",
            "Стадіон",
            "Кладовище",
            "Ферма",
            "Лікарня",
            "Університет",
            "Перукарня",
            "Готель",
            "Офіс",
            "Автобусна зупинка",
            "Торговий центр",
            "Табір",
            "Порт",
            "Пекарня",
            "Автосервіс",
            "Пошта",
            "Концертний зал",
            "Картинг",
            "Військова база",
            "Шахта",
            "Кінний двір",
            "Цирк",
            "Галерея",
            "Боулінг"
        )
        val wordsEnBasic = listOf(
            "Apartment",
            "Supermarket",
            "Market",
            "Bunker",
            "Dump",
            "Garage",
            "Country house",
            "Church",
            "Elevator",
            "Escalator",
            "Park",
            "Casino",
            "Bank",
            "School",
            "Kindergarten",
            "Hotel",
            "Gas station",
            "Bar",
            "Restaurant",
            "Beach",
            "Desert",
            "Embassy",
            "Pharmacy",
            "Library",
            "Swimming pool",
            "Prison",
            "Airport",
            "Train station",
            "Subway station",
            "Zoo",
            "Cinema",
            "Theater",
            "Museum",
            "Warehouse",
            "Beauty salon",
            "Gym",
            "Stadium",
            "Cemetery",
            "Farm",
            "Hospital",
            "University",
            "Hairdresser",
            "Hotel",
            "Office",
            "Bus stop",
            "Shopping mall",
            "Camp",
            "Port",
            "Bakery",
            "Auto service",
            "Post office",
            "Concert hall",
            "Go-karting",
            "Military base",
            "Mine",
            "Stable",
            "Circus",
            "Gallery",
            "Bowling"

        )

        val wordsRuTransport = listOf(
            "Трамвай",
            "Тролейбус",
            "Автобус",
            "Легковая машина",
            "Грузовик",
            "Фура",
            "Метро",
            "Трактор",
            "Экскаватор",
            "Подъемный кран",
            "Мусоровоз",
            "Карета",
            "Корабль",
            "Ракета",
            "Самолет",
            "Вертолет",
            "Фуникулер",
            "Поезд",
            "Электричка",
            "Мотоцикл",
            "Самокат",
            "Велосипед",
            "Катер",
            "Яхта",
            "Лодка",
            "Гидроцикл",
            "Танк",
            "Лимузин",
            "Квадроцикл",
            "Параплан",
            "Сап",
            "Байдарка",
        )

        val wordsUkTransport = listOf(
            "Трамвай",
            "Тролейбус",
            "Автобус",
            "Легковий автомобіль",
            "Вантажівка",
            "Фура",
            "Метро",
            "Трактор",
            "Екскаватор",
            "Підйомний кран",
            "Сміттєвоз",
            "Карета",
            "Корабель",
            "Ракета",
            "Літак",
            "Гелікоптер",
            "Фунікулер",
            "Потяг",
            "Електричка",
            "Мотоцикл",
            "Самокат",
            "Велосипед",
            "Катер",
            "Яхта",
            "Човен",
            "Гідроцикл",
            "Танк",
            "Лімузин",
            "Квадроцикл",
            "Параплан",
            "Сап",
            "Байдарка",
        )

        val wordsEnTransport = listOf(
            "Tram",
            "Trolleybus",
            "Bus",
            "Car",
            "Truck",
            "Trailer",
            "Subway",
            "Tractor",
            "Excavator",
            "Cran",
            "Garbage truck",
            "Carriage",
            "Ship",
            "Rocket",
            "Airplane",
            "Helicopter",
            "Cable car",
            "Train",
            "Commuter train",
            "Motorcycle",
            "Scooter",
            "Bicycle",
            "Boat",
            "Yacht",
            "Hydrocycle",
            "Tank",
            "Limousine",
            "Quad bike",
            "Paramotor",
            "SUP",
            "Kayak",
        )

        val wordsRuCountries = listOf(
            "Украина",
            "США",
            "Германия",
            "Испания",
            "Италия",
            "Франция",
            "Нидерланды",
            "Великобритания",
            "Китай",
            "Северная Корея",
            "Япония",
            "Бразилия",
            "Мексика",
            "Турция",
            "Австралия",
            "Казахстан",
            "Египет",
            "Грузия",
            "Сомали",
            "Польша",
            "Тайланд",
        )

        val wordsUkCountries = listOf(
            "Україна",
            "США",
            "Німеччина",
            "Іспанія",
            "Італія",
            "Франція",
            "Нідерланди",
            "Великобританія",
            "Китай",
            "Північна Корея",
            "Японія",
            "Бразилія",
            "Мексика",
            "Туреччина",
            "Австралія",
            "Казахстан",
            "Єгипет",
            "Грузія",
            "Сомалі",
            "Польща",
            "Таїланд",
        )

        val wordsEnCountries = listOf(
            "Ukraine",
            "USA",
            "Germany",
            "Spain",
            "Italy",
            "France",
            "Netherlands",
            "United Kingdom",
            "China",
            "North Korea",
            "Japan",
            "Brazil",
            "Mexico",
            "Turkey",
            "Australia",
            "Kazakhstan",
            "Egypt",
            "Georgia",
            "Somalia",
            "Poland",
            "Thailand",
        )


        wordsRuBasic.forEach {
            addWord(
                Word(it, false),
                "Базовый",
                "ru"
            )
        }
        wordsUkBasic.forEach {
            addWord(
                Word(it, false),
                "Базовий",
                "uk"
            )
        }
        wordsEnBasic.forEach {
            addWord(
                Word(it, false),
                "Basic",
                "en"
            )
        }

        wordsRuTransport.forEach {
            addWord(
                Word(it, false),
                "Транспорт",
                "ru"
            )
        }
        wordsUkTransport.forEach {
            addWord(
                Word(it, false),
                "Транспорт",
                "uk"
            )
        }
        wordsEnTransport.forEach {
            addWord(
                Word(it, false),
                "Transport",
                "en"
            )
        }

        wordsRuCountries.forEach {
            addWord(
                Word(it, false),
                "Страны",
                "ru"
            )
        }
        wordsUkCountries.forEach {
            addWord(
                Word(it, false),
                "Країни",
                "uk"
            )
        }
        wordsEnCountries.forEach {
            addWord(
                Word(it, false),
                "Countries",
                "en"
            )
        }

    }


}