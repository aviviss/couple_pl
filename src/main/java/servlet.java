import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Сервлет для отображения списка идей для совместного досуга.
 * Читает параметры инициализации из web.xml и динамические параметры из URL.
 *
 * @author avv
 * @version 1.0
 */
public class servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Настройка кодировки
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // Чтение параметров инициализации из web.xml
        String coupleName = getServletConfig().getInitParameter("couple_name");
        String appVersion = getServletConfig().getInitParameter("app_version");

        // Чтение динамического параметра из URL
        String category = request.getParameter("category");
        if (category == null || category.isEmpty()) {
            category = getServletConfig().getInitParameter("default_category");
        }

        // Генерация HTML-ответа
        PrintWriter out = response.getWriter();
        try {
            out.println("<!DOCTYPE html>");
            out.println("<html><head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<title>Планировщик досуга</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; margin: 40px; background-color: #f9f9f9; }");
            out.println("h1 { color: #d9534f; }");
            out.println("table { border-collapse: collapse; width: 100%; background: white; }");
            out.println("th, td { border: 1px solid #ddd; padding: 12px; text-align: left; }");
            out.println("th { background-color: #d9534f; color: white; }");
            out.println("tr:nth-child(even) { background-color: #f2f2f2; }");
            out.println(".status { padding: 5px 10px; border-radius: 12px; color: white; }");
            out.println(".planned { background-color: #5bc0de; }");
            out.println(".done { background-color: #5cb85c; }");
            out.println("</style>");
            out.println("</head><body>");

            out.println("<h1>Идеи для " + coupleName + "</h1>");
            out.println("<p><i>Версия: " + appVersion + " | Категория: " + category + "</i></p>");

            out.println("<table>");
            out.println("<tr><th>Идея</th><th>Категория</th><th>Статус</th><th>Кто предложил</th></tr>");

            // Имитация данных
            if ("movies".equals(category) || "all".equals(category)) {
                out.println("<tr><td>Посмотреть 'Начало'</td><td>Фильмы</td>");
                out.println("<td><span class='status planned'>Запланировано</span></td><td>Алексей</td></tr>");
            }
            if ("food".equals(category) || "all".equals(category)) {
                out.println("<tr><td>Приготовить пасту Карбонара</td><td>Еда</td>");
                out.println("<td><span class='status done'>Выполнено</span></td><td>Мария</td></tr>");
            }
            if ("walks".equals(category) || "all".equals(category)) {
                out.println("<tr><td>Прогулка по набережной</td><td>Прогулки</td>");
                out.println("<td><span class='status planned'>Запланировано</span></td><td>Мария</td></tr>");
            }

            out.println("</table>");
            out.println("</body></html>");
        } finally {
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}