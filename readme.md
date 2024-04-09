# Hakkında

Bu proje, bilişim sektöründeki bir firmanın isteği üzerine yaratılmıştır. Aşağıdaki açıklamalar proje hakkında temel bilgiler içermektedir:

- Projede, hızlı kullanım için h2 database entegre edilmiştir. (`localhost:8080/h2-console`)
- Swagger kullanılmış olup, endpointlerin kolay bir şekilde denenmesi amaçlanmıştır.
- Data Controller'da örnek veri oluşturmak için bir endpoint'e yer verilmiştir.
- İlişkiler, geliştirmenin kolay sağlanabilmesi adına çok sıkı yapılmamıştır.
- Özelleştirilmiş istisnalara yer verilmemiştir.

## Spring Boot E-Ticaret Projesi

Bu proje, e-ticaret platformu için Spring Boot kullanarak geliştirilmiş bir projedir. Projede, müşterilerin ürünleri sepete ekleyebilmesi, sipariş verebilmesi ve sipariş geçmişini görüntüleyebilmesi gibi temel işlevler bulunmaktadır. Ayrıca, ürün fiyatları güncellendiğinde geçmişe yönelik fiyat bilgilerinin saklanması ve stok takibi yapılması gibi özellikler de bulunmaktadır.

## EndPointler

Aşağıdaki endpointler API'nin kullanımını sağlar:

### Müşteri İşlemleri

- **Müşteri Ekleme**: `POST /api/v1/dev/customer/add-customer`

### Ürün İşlemleri

- **Ürünleri Listeleme**: `GET /api/v1/dev/product/get-product`
- **Ürün Oluşturma**: `POST /api/v1/dev/product/create-product`
- **Ürün Güncelleme**: `PUT /api/v1/dev/product/update-product`
- **Ürün Silme**: `DELETE /api/v1/dev/product/delete-product`
- **Tüm Ürün Sepetlerini Getirme**: `GET /api/v1/dev/product/get-all-product-carts`

### Sepet İşlemleri

- **Sepeti Getirme**: `GET /api/v1/dev/cart/get-cart`
- **Sepeti Güncelleme**: `PUT /api/v1/dev/cart/update-cart`
- **Sepeti Boşaltma**: `DELETE /api/v1/dev/cart/empty-cart`
- **Sepete Ürün Ekleme**: `POST /api/v1/dev/cart/add-product-to-cart`
- **Sepetten Ürün Çıkarma**: `DELETE /api/v1/dev/cart/remove-product-from-cart`
- **Sepet İçindeki Ürünleri Getirme**: `GET /api/v1/dev/cart/get-cart-items`

### Sipariş İşlemleri

- **Sipariş Koduna Göre Siparişi Getirme**: `GET /api/v1/dev/order/get-order-for-code`
- **Sipariş Oluşturma**: `POST /api/v1/dev/order/place-order`
- **Bir Müşteriye Ait Tüm Siparişleri Getirme**: `GET /api/v1/dev/order/get-all-orders-for-customer`
- **Sipariş İşlemini Başlatma**: `POST /api/v1/dev/order/start-order-process`

### Data İşlemleri

- **Örnek Veri Oluşturma**: `GET /api/v1/dev/data/create-sample-data`

## Kullanılan Teknolojiler

Proje, aşağıdaki teknolojileri ve kütüphaneleri kullanmaktadır:

- **Spring Boot**: Hızlı ve kolay bir şekilde Spring tabanlı uygulamalar geliştirmek için kullanılan bir çerçeve.
- **Spring Data JPA**: Veritabanı işlemlerini kolaylaştırmak için kullanılan bir Spring modülü.
- **Spring Web**: Web tabanlı uygulamalar geliştirmek için kullanılan bir Spring modülü.
- **Hibernate**: Veritabanı işlemlerini yönetmek için kullanılan bir ORM (Object-Relational Mapping) aracı.
- **Lombok**: Java için bir kütüphane, tekrarlayıcı kodu azaltmak ve sınıf dosyalarını daha okunabilir hale getirmek için kullanılır.
- **h2**: Veritabanı olarak kullanılan ilişkisel bir veritabanı yönetim sistemi.
