package pondero.ui.participants;

import pondero.L10n;
import pondero.model.entities.Participant;

public class ParticipantReport {

    public static String getHtml(Participant participant) {
        StringBuilder html = new StringBuilder("<html>");
        if (participant == null) {
            html.append("Nu s-a ales nici un participant");
        } else {
            html.append("<center><h1>");
            html.append(participant.getSurname()).append(" ").append(participant.getName());
            html.append("</h1></center>");

            html.append("<table cellborder='0'>");
            html.append("<tr>");
            html.append("<td align='right'><h2>").append(L10n.getString("lbl.participant.age")).append(": ").append("</h2></td>");
            html.append("<td><h2 color='blue'><i>").append(participant.getAge()).append(" ").append(years(participant.getAge())).append("</i></h2></td>");
            html.append("</tr>");
            html.append("<tr>");
            html.append("<td align='right'><h2>").append(L10n.getString("lbl.participant.gender")).append(": ").append("</h2></td>");
            html.append("<td><h2 color='blue'><i>").append(participant.getGender()).append("</i></h2></td>");
            html.append("</tr>");
            html.append("<tr>");
            html.append("<td align='right'><h2>").append(L10n.getString("lbl.participant.driving-age")).append(": ").append("</h2></td>");
            html.append("<td><h2 color='blue'><i>").append(participant.getDrivingAge()).append(" ").append(years(participant.getDrivingAge())).append("</i></h2></td>");
            html.append("</tr>");
            html.append("</table>");
        }
        html.append("</html>");
        return html.toString();
    }

    private static String years(int age) {
        if (age == 1) { return "an"; }
        if (age == 0 || 1 < age && age < 20) { return "ani"; }
        return "de ani";
    }
}
