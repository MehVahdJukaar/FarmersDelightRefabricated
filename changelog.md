- Updated for parity with Farmer's Delight 1.2.8.

## Bugfixes
- Fixed packet decode failure upon attempting to join servers without Farmer's Delight Refabricated. [#56](https://github.com/MehVahdJukaar/FarmersDelightRefabricated/issues/56)
- Fixed a classloading issue preventing the ItemLike (Mojmap) interface from having mixins applied. [#77](https://github.com/MehVahdJukaar/FarmersDelightRefabricated/issues/77)
- Fixed Torchflower cutting recipe incorrectly using a `forge` tag rather than a `c` tag. [#160](https://github.com/MehVahdJukaar/FarmersDelightRefabricated/issues/160)
- Fixed error log when starting server due to a missing client class. [#174](https://github.com/MehVahdJukaar/FarmersDelightRefabricated/issues/174)