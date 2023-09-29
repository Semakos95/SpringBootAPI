package gr.cinema.api.constants;

public interface WebConstants {

    //GENERAL PATH
    String ID_PATH = "/{id}"; //Url path

    //GENERAL PATH VARIABLES
    String ID = "id"; //@PathVariable
    String NAME = "name";
    String PAGE = "/page";

    //AuthController
    String AUTH = "/api/auth";
    String AUTH_LOGIN = "/login";
    String AUTH_REGISTER = "/register";


    //AdminController
    String ADMIN = "/admin";
    String ADMIN_LOGIN = "/login";
    String ADMIN_GET_ALL_USERS_BY_NAME = "/allUsersByName";
    String ADMIN_GET_ALL_USERS = "/allUsers";


    //UserController
    String USER = "/api/user";
    String USER_LOGIN = "/login";
    String GET_ALL_USERS_BY_NAME = "/allUsersByName";
    String GET_ALL_USERS = "/allUsers";


    //PerformanceController
    String PERFORMANCE_CONTROLLER_GET_BY_TITLE = "/api/performance/byTitle";
    String PERFORMANCE_CONTROLLER_GET_ALL = "/api/performance/getAll";
    String PERFORMANCE_CONTROLLER_GET_PERFORMANCE = "/api/performance/getPerformance" + ID_PATH;
    String PERFORMANCE_CONTROLLER_INSERT = "/api/performance/insert";
    String PERFORMANCE_CONTROLLER_UPDATE = "/api/performance/update" + ID_PATH;
    String PERFORMANCE_CONTROLLER_DELETE = "/api/performance/delete" + ID_PATH;
    String PERFORMANCE_CONTROLLER_GET_ALL_BY_USER_ID = "/api/performance/getAllByUserId" + ID_PATH;
    String PERFORMANCE_CONTROLLER_GET_ALL_PERFORMANCES_TO_STAGE = "/api/performance/getAllToStage";
    String PERFORMANCE_CONTROLLER_GET_ALL_STAGED_BY_DATE = "/api/performance/getAllStagedByDate";

    //PerformancePriceController
    String PERF_PRICE_CONTROLLER_GET_ALL = "/api/performancePrice/getAll";
    String PERF_PRICE_CONTROLLER_GET_PERF_PRICE_BY_ID = "/api/performancePrice/getPerfromancePrice" + ID_PATH;
    String PERF_PRICE_CONTROLLER_INSERT = "/api/performancePrice/insert";
    String PERF_PRICE_CONTROLLER_UPDATE = "/api/performancePrice/update" + ID_PATH;
    String PERF_PRICE_CONTROLLER_DELETE = "/api/performancePrice/delete" + ID_PATH;
    String PERF_PRICE_CONTROLLER_GET_ALL_BY_PERFORMANCE_ID = "/api/performancePrice/getAllByPerformanceId" + ID_PATH;

    //PerformanceStagingController
    String PERF_STAGING_CONTROLLER_GET_ALL = "/api/performanceStaging/getAll";
    String PERF_STAGING_CONTROLLER_GET_PERF_STAGING = "/api/performanceStaging/getPerformanceStaging" + ID_PATH;
    String PERF_STAGING_CONTROLLER_INSERT = "/api/performanceStaging/insert";
    String PERF_STAGING_CONTROLLER_UPDATE = "/api/performanceStaging/update" + ID_PATH;
    String PERF_STAGING_CONTROLLER_DELETE = "/api/performanceStaging/delete" + ID_PATH;
    String PERF_STAGING_CONTROLLER_GET_ALL_BY_PERFORMANCE_ID = "/api/performanceStaging/getAllByPerformanceId" + ID_PATH;
    String PERF_STAGING_CONTROLLER_GET_FIRST_AND_LAST_DATE = "/api/performanceStaging/getFirstAndLastDate" + ID_PATH;
    String PERF_STAGING_CONTROLLER_GET_ALL_TO_STAGE = "/api/performanceStaging/getToStage" + ID_PATH;
    String PERF_STAGING_CONTROLLER_GET_ALL_TO_STAGE_BY_PERFORMANCE_ID = "/api/performanceStaging/getAllPerformanceStagingsToStageByPerfId" + ID_PATH;

    //RentController
    String RENT_CONTROLLER_GET_ALL = "/api/rent/getAll";
    String RENT_CONTROLLER_GET_RENT = "/api/rent/getRent" + ID_PATH;
    String RENT_CONTROLLER_INSERT = "/api/rent/insert";
    String RENT_CONTROLLER_UPDATE = "/api/rent/update" + ID_PATH;
    String RENT_CONTROLLER_DELETE = "/api/rent/delete" + ID_PATH;
    String RENT_CONTROLLER_GET_ALL_BY_USER_ID = "/api/rent/getAllByUserId" + ID_PATH;
    String RENT_CONTROLLER_GET_ALL_RENTS_BY_DATE = "/api/rent/getAllByDate";

    //RoomController
    String ROOM_CONTROLLER_GET_ALL = "/api/room/getAll";
    String ROOM_CONTROLLER_GET_ROOM = "/api/room/getRoom" + ID_PATH;
    String ROOM_CONTROLLER_INSERT_ROOM = "/api/room/insert";
    String ROOM_CONTROLLER_UPDATE_ROOM = "/api/room/update" + ID_PATH;
    String ROOM_CONTROLLER_DELETE_ROOM ="/api/room/delete" + ID_PATH;

    //SectionController
    String SECTION_CONTROLLER_GET_ALL = "/api/section/getAll";
    String SECTION_CONTROLLER_GET_SECTION = "/api/section/getSection" + ID_PATH;
    String SECTION_CONTROLLER_INSERT_SECTION = "/api/section/insert";
    String SECTION_CONTROLLER_UPDATE_SECTION = "/api/section/update" + ID_PATH;
    String SECTION_CONTROLLER_DELETE_SECTION = "/api/section/delete" + ID_PATH;
    String SECTION_CONTROLLER_GET_BY_NAME_AND_ROOM_ID = "/api/section/getSectionByNameAndRoomId";

    //TicketController
    String TICKET_CONTROLLER_GET_ALL = "/api/ticket/getAll";
    String TICKET_CONTROLLER_GET_TICKET = "/api/ticket/getTicket" + ID_PATH;
    String TICKET_CONTROLLER_INSERT = "/api/ticket/insert";
    String TICKET_CONTROLLER_UPDATE = "/api/ticket/update" + ID_PATH;
    String TICKET_CONTROLLER_DELETE = "/api/ticket/delete" + ID_PATH;
    String TICKET_CONTROLLER_SPECIFIC_TICKETS = "/api/ticket/getSpecificTickets";

    //OnlineTicketController
    String ONLINE_TICKET_CONTROLLER_GET_ALL = "/api/onlineTicket/allOnlineTickets";
    String ONLINE_TICKET_CONTROLLER_GET_ONLINE_TICKET = "/api/onlineTicket/getTicket" + ID_PATH;
    String ONLINE_TICKET_CONTROLLER_INSERT = "/api/onlineTicket/insert";
    String ONLINE_TICKET_CONTROLLER_UPDATE = "/api/onlineTicket/update" + ID_PATH;
    String ONLINE_TICKET_CONTROLLER_DELETE = "/api/onlineTicket/delete" + ID_PATH;
    String ONLINE_TICKET_CONTROLLER_INSERT_ALL = "/api/onlineTicket/insertAll";

    //ImageController
    String IMAGE_CONTROLLER_INSERT = "/api/image/insert" + ID_PATH;
    String IMAGE_CONTROLLER_UPDATE = "/api/image/update" + ID_PATH;
    String IMAGE_CONTROLLER_DOWNLOAD = "/api/image/download" + ID_PATH;
}
