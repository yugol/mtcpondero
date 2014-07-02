package pondero.ui.participants;

import pondero.model.entities.Participant;

public class ParticipantReport {

    public static String getHtml(Participant participant) {
        StringBuilder html = new StringBuilder("<html>");
        if (participant == null) {
            html.append("<h1>");
            html.append("V\u0103 rug\u0103m s\u0103 alege\u021Bi un participant");
            html.append("</h1>");
        } else {
            html.append("<h1>");
            html.append(participant.getSurname());
            html.append(" ");
            html.append(participant.getName());
            html.append("</h1>");
        }
        html.append("</html>");
        return html.toString();
    }

}
