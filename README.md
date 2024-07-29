# TestCaseIdeaSoft

Bu proje, MyIdeaSoft web sitesinde belirli test senaryolarını gerçekleştirmek için Selenium ve TestNG kullanılarak yazılmıştır. Aşağıda, test senaryoları ve projenin nasıl çalıştırılacağı ile ilgili bilgiler yer almaktadır.

## Gereksinimler

- Java JDK (11 veya üzeri)
- Maven
- Google Chrome Tarayıcı
- ChromeDriver
  
## Kurulum

1. Bu projeyi klonlayın:

   ```sh
   git clone https://github.com/kullaniciadi/testCaseMyIdeaSoft.git
   cd testCaseIdeaSoft
2. mvn clean install

## Test Cases

```markdown

1. Web Sitesini Ziyaret Etme

- Test Metodu: `testVisitWebsite()`
- Açıklama: Web sitesine gidilir ve anasayfanın yüklendiğini doğrulamak için logo kontrol edilir.

2. Ürün Arama

- Test Metodu: `testSearchProduct()`
- Açıklama: Arama kutusuna "ürün" yazılır ve arama yapılır.

3. Ürün Seçimi

- Test Metodu: `testSelectProduct()`
- Açıklama: Arama sonuçlarından ilk ürün seçilir.

4. Ürünü Sepete Ekleme

- Test Metodu: `testAddProductToCart()`
- Açıklama: Seçilen üründen 5 adet seçilir ve sepete eklenir. "SEPETİNİZE EKLENMİŞTİR" mesajı kontrol edilir.

5. Sepeti Doğrulama

- **Test Metodu**: `testVerifyCart()`
- **Açıklama**: Sepet sayfasına gidilir ve sepetteki ürün miktarının 5 olduğu doğrulanır.
```

## Kullanılan Teknolojiler

- **Selenium**: Web tarayıcı otomasyonu için.
- **TestNG**: Test yönetimi ve raporlama için.
- **WebDriverManager**: ChromeDriver ve diğer tarayıcı sürücüleri için.

## Çalıştırma

Testleri çalıştırmak için aşağıdaki komutları kullanabilirsiniz:

1. **IDE Kullanarak**: Test dosyasına sağ tıklayın ve "Çalıştır" seçeneğini seçin.
2. **Maven Kullanarak**:

   ```sh
   mvn test


