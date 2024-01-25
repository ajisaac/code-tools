package co.aisaac.tools.code_indexing;

public enum ComponentType {
    DTO,
    ENTITY,
    DATA,
    REPOSITORY,
    CONTROLLER,
    SERVICE_INTERFACE,
    SERVICE_IMPLEMENTATION,
    STATELESS_TASK,
    POLLER,
    MESSAGE_CONSUMER,
    RESPONSE_HANDLER,
    REQUEST_HANDLER,
    TASK,
    TASK_IMPL,
    HANDLER_IMPL,
    PACKAGE,
    TYPE,
    METRICS,
    MESSAGE_LISTENER,
    EXCEPTION,
    VALIDATOR,
    UTILS,
    CONTEXT,
    APPLICATION,
    CONFIG,
    UNKNOWN;

    /**
     * Determine the component type from the class name. AwsBillingService -> SERVICE
     */
    public static ComponentType fromClassName(String className) {
        if (className.endsWith("DTO")) return DTO;
        if (className.endsWith("Repository")) return REPOSITORY;
        if (className.endsWith("RepositoryImpl")) return REPOSITORY;
        if (className.endsWith("Service")) return SERVICE_INTERFACE;
        if (className.endsWith("ServiceImpl")) return SERVICE_IMPLEMENTATION;
        if (className.endsWith("StatelessTask")) return STATELESS_TASK;
        if (className.endsWith("Controller")) return CONTROLLER;
        if (className.endsWith("Poller")) return POLLER;
        if (className.endsWith("Entity")) return ENTITY;
        if (className.endsWith("Data")) return DATA;
        if (className.endsWith("Application")) return APPLICATION;
        if (className.endsWith("Config")) return CONFIG;
        if (className.endsWith("MessageConsumer")) return MESSAGE_CONSUMER;
        if (className.endsWith("Type")) return TYPE;
        if (className.endsWith("Metrics")) return METRICS;
        if (className.endsWith("ResponseHandler")) return RESPONSE_HANDLER;
        if (className.endsWith("RequestHandler")) return REQUEST_HANDLER;
        if (className.endsWith("Exception")) return EXCEPTION;
        if (className.endsWith("MessageListener")) return MESSAGE_LISTENER;
        if (className.endsWith("Context")) return CONTEXT;
        if (className.endsWith("Task")) return TASK;
        if (className.endsWith("TaskImpl")) return TASK_IMPL;
        if (className.endsWith("Validator")) return VALIDATOR;
        if (className.endsWith("Utils")) return UTILS;
        if (className.endsWith("Util")) return UTILS;
        if (className.endsWith("HandlerImpl")) return HANDLER_IMPL;
        if (className.endsWith("Package")) return PACKAGE;

        return UNKNOWN;
    }
}
