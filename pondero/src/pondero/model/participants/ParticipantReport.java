package pondero.model.participants;

import pondero.L10n;

public class ParticipantReport {

    public static String getHtml(final Participant participant) {
        final StringBuilder html = new StringBuilder("<html>");
        if (participant == null) {
            html.append(L10n.getString("msg.no-participant-selected"));
        } else {
            html.append("<center><h1>");
            html.append(participant.getSurname()).append(" ").append(participant.getName());
            html.append("</h1></center>");
            html.append("<p>&nbsp;</p>");
            html.append("<table cellborder='0'>");
            html.append("<tr>");
            html.append("<td align='right' color='gray'><b>").append(L10n.getString("lbl.participant.age")).append(": ").append("</b></td>");
            html.append("<td><i>").append(participant.getAge()).append(" ").append(years(participant.getAge())).append("</i></td>");
            html.append("</tr>");
            html.append("<tr>");
            html.append("<td align='right' color='gray'><b>").append(L10n.getString("lbl.participant.gender")).append(": ").append("</b></td>");
            html.append("<td><i>").append(participant.getGender()).append("</i></td>");
            html.append("</tr>");
            html.append("<tr>");
            html.append("<td align='right' color='gray'><b>").append(L10n.getString("lbl.participant.driving-age")).append(": ").append("</b></td>");
            html.append("<td><i>").append(participant.getDrivingAge()).append(" ").append(years(participant.getDrivingAge())).append("</i></td>");
            html.append("</tr>");
            html.append("</table>");
        }
        html.append("</html>");
        return html.toString();
    }

    private static String years(final int age) {
        if (age == 1) { return "an"; }
        if (age == 0 || 1 < age && age < 20) { return "ani"; }
        return "de ani";
    }
}
