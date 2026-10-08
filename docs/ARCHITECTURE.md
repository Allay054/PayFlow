# PayFlow Architecture

## Overview

PayFlow is a portfolio-grade Android digital wallet and payment
sandbox application.

No real money is processed.

All payment operations will use sandbox/demo transactions.

---

## Android Architecture

PayFlow follows MVVM and Clean Architecture.

```text
Presentation
     |
     v
 ViewModel
     |
     v
 Use Cases
     |
     v
Repository Interface
     |
     v
Data Layer
   /     \
 Room   Retrofit