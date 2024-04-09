# Hakkında

Bilişim sektöründeki bir firmanın isteği üzerine yaratılmıştır.

## Açıklama

- Projede, hizli kullanim için h2 database entegre edilmiştir. (localhost:8080/h2-console)
- Swagger kullanılmış olup end-pointlerin kolay bir şekilde denenmesi amaçlanmıştır.
- Aynı zamanda Data Controller'da örnek data örneği oluşturmak için bir endpoint'e yer verilmiştir.
- İlişkiler, geliştirmenin kolay sağlanabilmesi adına çok sıkı yapılmamıştır.
- Özelleştirilmiş istisnalara yer verilmemiştir.

# Spring Boot E-Ticaret Projesi

Bu proje, e-ticaret platformu için Spring Boot kullanarak geliştirilmiş bir projedir. Projede, müşterilerin ürünleri sepete ekleyebilmesi, sipariş verebilmesi ve sipariş geçmişini görüntüleyebilmesi gibi temel işlevler bulunmaktadır. Ayrıca, ürün fiyatları güncellendiğinde geçmişe yönelik fiyat bilgilerinin saklanması ve stok takibi yapılması gibi özellikler de bulunmaktadır

## Proje Yapısı

Proje, aşağıdaki bileşenleri içermektedir:
- **Product Service**: Ürünlerin eklenmesi, güncellenmesi, silinmesi ve görüntülenmesi işlemlerini gerçekleştiren servisler.
- **Customer Service**: Müşteri yönetimi için kullanılan servisler.
- **Cart Service**: Sepet yönetimi için kullanılan servisler.
- **Order Service**: Sipariş yönetimi için kullanılan servisler.

## Servisler ve Kullanılan Endpoint'ler

Aşağıda, projede bulunan servisler ve bu servislerin sağladığı endpoint'ler listelenmiştir:

- **AddCustomer**: Yeni müşteri eklemek için kullanılan servis.
- POST /customers
- **GetProduct**: Ürün bilgilerini almak için kullanılan servis.
    - GET /products/{productId}
- **CreateProduct**: Yeni ürün eklemek için kullanılan servis.
    - POST /products
- **UpdateProduct**: Ürün bilgilerini güncellemek için kullanılan servis.
    - PUT /products/{productId}
- **DeleteProduct**: Ürünü silmek için kullanılan servis.
    - DELETE /products/{productId}
- **GetCart**: Müşterinin sepetini almak için kullanılan servis.
    - GET /carts/{customerId}
- **UpdateCart**: Müşterinin sepetini güncellemek için kullanılan servis.
    - PUT /carts/{customerId}
- **EmptyCart**: Müşterinin sepetini boşaltmak için kullanılan servis.
    - DELETE /carts/{customerId}
- **PlaceOrder**: Yeni sipariş oluşturmak için kullanılan servis.
    - POST /orders
- **GetOrderForCode**: Belirli bir sipariş koduna sahip siparişi almak için kullanılan servis.
    - GET /orders/{orderCode}
- **GetAllOrdersForCustomer**: Bir müşteriye ait tüm siparişleri almak için kullanılan servis.
    - GET /orders/customer/{customerId}
- **AddProductToCart**: Sepete ürün eklemek için kullanılan servis.
    - POST /carts/{customerId}/products
- **RemoveProductFromCart**: Sepetten ürün çıkarmak için kullanılan servis.
    - DELETE /carts/{customerId}/products/{productId}

## Kullanılan Teknolojiler

Proje, aşağıdaki teknolojileri ve kütüphaneleri kullanmaktadır:

- **Spring Boot**: Hızlı ve kolay bir şekilde Spring tabanlı uygulamalar geliştirmek için kullanılan bir çerçeve.
- **Spring Data JPA**: Veritabanı işlemlerini kolaylaştırmak için kullanılan bir Spring modülü.
- **Spring Web**: Web tabanlı uygulamalar geliştirmek için kullanılan bir Spring modülü.
- **Hibernate**: Veritabanı işlemlerini yönetmek için kullanılan bir ORM (Object-Relational Mapping) aracı.
- **PostgreSQL**: Veritabanı olarak kullanılan ilişkisel bir veritabanı yönetim sistemi.

