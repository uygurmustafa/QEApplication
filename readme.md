# Web Otomasyon Test Framework'u

Bu proje, web uygulamaları için kapsamlı bir test otomasyon framework'üdür. Selenium WebDriver, Gauge ve Allure Report kullanılarak geliştirilmiştir.

## 🚀 Özellikler
- Fonksiyonel test otomasyonu
- Performans metrikleri ölçümü
- Görsel regresyon testleri
- Erişilebilirlik kontrolleri
- Detaylı raporlama (Allure Report)
- BDD yaklaşımı (Gauge)

## 📋 Ön Gereksinimler
- Java JDK 8 veya üzeri
- Maven 3.6.0 veya üzeri
- Chrome/Firefox tarayıcı
- Gauge CLI
- Allure CLI

### Kurulum Adımları

#### 1. Java JDK kurulumu:
```bash
# MacOS için
brew install openjdk@11

# Windows için
choco install openjdk11
```

#### 2. Maven kurulumu:
```bash
# MacOS için
brew install maven

# Windows için
choco install maven
```

#### 3. Gauge kurulumu:
```bash
# MacOS için
brew install gauge

# Windows için
choco install gauge
```

#### 4. Allure kurulumu:
```bash
# MacOS için
brew install allure

# Windows için
scoop install allure
```

## 🔧 Proje Kurulumu

1. Bu repository'yi klonlayın.
```bash
git clone https://github.com/your-username/web-automation.git
cd web-automation
```

2. Proje bağımlılıklarını yükleyin.
```bash
mvn clean install
```

3. Gauge yükleyin:
```bash
gauge install java
gauge install html-report
gauge install json-report
gauge install xml-report
```

4. Testleri çalıştırın.
```bash
mvn clean test
```

#### Allure Report oluşturun
```bash
# Rapor oluştur
mvn allure:report

# Raporu görüntüle
mvn allure:serve
```

## 📁 Proje Yapısı
```
src/
├── test/
│   ├── java/
│   │   └── com/
│   │       └── testinium/
│   │           ├── base/
│   │           │   ├── BasePage.java
│   │           │   └── BaseTest.java
│   │           ├── pages/
│   │           │   ├── LoginPage.java
│   │           │   └── ...
│   │           ├── step/
│   │           │   ├── LoginSteps.java
│   │           │   └── ...
│   │           └── utils/
│   │               ├── PerformanceUtil.java
│   │               ├── VisualUtil.java
│   │               └── AccessibilityUtil.java
│   └── resources/
│       ├── allure.properties
│       ├── environment.properties
│       └── categories.json
└── specs/
    └── example.spec
```

## 🔍 Test Yazma Rehberi

### Yeni bir test senaryosu ekleme:
1. `specs` klasöründe yeni bir `.spec` dosyası oluşturun:

```
# Login İşlemleri
Tags: login, smoke

## Başarılı Login
* Login with username "test@user.com" and password "password123"
* Verify successful login
```

2. Step tanımlarını oluşturun:
```java
@Step("Login with username <username> and password <password>")
public void login(String username, String password) {
    // step implementation
}
```

2. Sayfaları ve test classlarını olusturun:
```java

public class ExamplePage extends BaseTest {}

public class ExampleSteps {}
```

## 📊 Raporlama

### Allure Report Özellikleri:
- Test sonuçları özeti
- Detaylı test adımları
- Ekran görüntüleri
- Performans metrikleri
- Erişilebilirlik raporları
- Görsel regresyon sonuçları

## 🔧 Sorun Giderme

### Sık Karşılaşılan Sorunlar
1. **WebDriver hatası:**
```bash
# Chrome driver'ı güncelle
webdriver-manager update
```

2. **Gauge plugin hatası:**
```bash
# Gauge plugin'lerini yeniden yükle
gauge uninstall java
gauge install java
```

3. **Allure rapor hatası:**
```bash
# Allure sonuçlarını temizle
rm -rf target/allure-results
mvn clean test
```

## 🤝 Katkıda Bulunma
- Fork yapın
- Feature branch oluşturun
- Değişikliklerinizi commit edin
- Branch'inizi push edin
- Pull request oluşturun

## 📝 Test Yazım Kuralları
- Her test için açıklayıcı isim kullanın
- Test metodlarına uygun annotation'ları ekleyin
- Allure için gerekli etiketleri ekleyin
- Her testin bağımsız çalışabildiğinden emin olun

## 📞 Destek
Sorularınız için:
- Issue açın
- Pull request gönderin
- Wiki sayfasını ziyaret edin

## 📜 Lisans
Bu proje MIT lisansı altında lisanslanmıştır.
