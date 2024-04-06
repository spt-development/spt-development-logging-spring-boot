## New Features

* Added logging for public methods of classes that extend `org.springframework.dao.support.DaoSupport`.
    * This logging support results in warnings being logged by `CglibAopProxy` when Spring Boot apps start, therefore this feature is disabled by default.
    * Enable by setting the `spt.logging.dao-support.enabled` property to `true`. 
    * Alternatively, accept the warnings logged at start-up or configure logging to *not* log `CglibAopProxy` warnings.

## Dependencies

* Aligned dependencies with [Spring Boot 3.2.5](https://github.com/spring-projects/spring-boot/releases/tag/v3.2.5)
