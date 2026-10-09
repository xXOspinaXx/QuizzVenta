# Respuestas: Patrones de Diseño Estructurales

## Pregunta 1

**a.** Decorator.

**b.** Permite agregar logging, compresión y otras funcionalidades envolviendo el objeto original y combinando decoradores. Así, `BasicMessage` no se modifica ni se necesita crear una clase para cada combinación.

## Pregunta 2

**a.** Facade.

**b.** `PurchaseFacade` ofrece una única operación para realizar la compra y coordina internamente los servicios necesarios. El controlador solo debe llamar a esa interfaz, sin conocer el orden ni los detalles de cada servicio.

## Pregunta 3

**a.** Proxy.

**b.** `InventoryProxy` verifica los permisos antes de delegar cada consulta a `RealInventoryService`. Implementa la misma interfaz, por lo que el cliente sigue usando el servicio de la misma forma y el servicio real no necesita modificarse.

## Pregunta 4

**a.** Adapter.

**b.** `PaymentAdapter` convierte la interfaz de pagos que espera la tienda en la interfaz de `ExternalPaymentService`. También adapta el monto a centavos; la clase externa permanece sin cambios.
