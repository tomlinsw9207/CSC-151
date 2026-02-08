import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class RaidersOrgAppv6 {
    private JFrame frame;
    private JPanel mainContent;
    private CardLayout cardLayout;

    private final Color RAIDERS_SILVER = new Color(165, 172, 175);
    private final Color RAIDERS_BLACK = new Color(0, 0, 0);

    public RaidersOrgAppv6() {
        frame = new JFrame("Las Vegas Raiders - Organization Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);

        cardLayout = new CardLayout();
        mainContent = new JPanel(cardLayout);

        mainContent.add(createHomePanel(), "Home");

        // --- PLAYER ROSTER (base data unchanged) ---
        String[][] playerData = new String[][]{
            {"Ashton Jeanty", "RB", "Boise State", "2"},
            {"Zamir White", "RB", "Georgia", "3"},
            {"Shedrick Jackson", "WR", "Auburn", "4"},
            {"AJ Cole", "P", "NC State", "6"},
            {"Geno Smith", "QB", "West Virginia", "7"},
            {"Daniel Carlson", "K", "Auburn", "8"},
            {"Tyree Wilson", "DE", "Texas Tech", "9"},
            {"Dont'e Thornton Jr.", "WR", "Tennessee", "10"},
            {"Jeremy Chinn", "SS", "Southern Illinois", "11"},
            {"Aidan O'Connell", "QB", "Purdue", "12"},
            {"Kenny Pickett", "QB", "Pittsburgh", "15"},
            {"Tyler Lockett", "WR", "Kansas State", "17"},
            {"Jack Bech", "WR", "TCU", "18"},
            {"Isaiah Pola-Mao", "S", "USC", "20"},
            {"Eric Stokes", "CB", "Georgia", "22"},
            {"Dylan Laube", "RB", "New Hampshire", "23"},
            {"Decamerion Richardson", "CB", "Mississippi St.", "25"},
            {"Darien Porter", "CB", "Iowa State", "26"},
            {"Tristin McCollum", "S", "Sam Houston St.", "27"},
            {"Terrell Edmunds", "S", "Virginia Tech", "28"},
            {"Chigozie Anusiem", "CB", "Colorado St.", "29"},
            {"Darnay Holmes", "CB", "UCLA", "30"},
            {"Raheem Mostert", "RB", "Purdue", "31"},
            {"Lonnie Johnson Jr.", "S", "Kentucky", "32"},
            {"Jamal Adams", "LB", "LSU", "33"},
            {"Tommy Eichenberg", "LB", "Ohio State", "44"},
            {"Devin White", "LB", "LSU", "45"},
            {"Carter Runyon", "TE", "Towson", "46"},
            {"Charles Snowden", "DE", "Virginia", "49"},
            {"Jacob Bobenmoyer", "LS", "N. Colorado", "50"},
            {"Malcolm Koonce", "DE", "Buffalo", "51"},
            {"Elandon Roberts", "LB", "Houston", "52"},
            {"Cody Lindenberg", "LB", "Minnesota", "55"},
            {"Atonio Mafi", "G", "UCLA", "56"},
            {"Jackson Powers-Johnson", "C", "Oregon", "58"},
            {"Jon Rhattigan", "LB", "Army", "59"},
            {"Charles Grant", "T", "William & Mary", "60"},
            {"Alex Cappa", "G", "Humboldt State", "65"},
            {"Dylan Parham", "G", "Memphis", "66"},
            {"Will Putnam", "C", "Clemson", "67"},
            {"Adam Butler", "DT", "Vanderbilt", "69"},
            {"Stone Forsythe", "OL", "Florida", "70"},
            {"DJ Glaze", "T", "Maryland", "71"},
            {"Kolton Miller", "T", "UCLA", "74"},
            {"Caleb Rogers", "G", "Texas Tech", "76"},
            {"Ian Thomas", "TE", "Indiana", "80"},
            {"Alex Bachman", "WR", "Wake Forest", "81"},
            {"Michael Mayer", "TE", "Notre Dame", "87"},
            {"Brock Bowers", "TE", "Georgia", "89"},
            {"Jahfari Harvey", "DE", "SMU", "91"},
            {"JJ Pegues", "DT", "Mississippi", "92"},
            {"Jonah Laulu", "DT", "Oklahoma", "96"},
            {"Tonka Hemingway", "DT", "South Carolina", "97"},
            {"Maxx Crosby", "DE", "Eastern Michigan", "98"},
            {"Thomas Booker IV", "DT", "Stanford", "99"}
        };
        mainContent.add(
            createDataPanel("Active Player Roster", playerData,
                            new String[]{"Name", "Position", "College", "Number"}),
            "Players"
        );

        // --- FRONT OFFICE (base data unchanged) ---
        String[][] frontOfficeData = new String[][]{
            {"Mark Davis", "Owner"},
            {"Sandra Douglass Morgan", "President"},
            {"John Spytek", "General Manager"},
            {"Brian Stark", "Assistant GM"},
            {"Tom Delaney", "SVP, Football Admin"},
            {"Mark Thewes", "SVP, Football Ops"}
        };
        mainContent.add(
            createDataPanel("Front Office", frontOfficeData,
                            new String[]{"Name", "Role"}),
            "Staff"
        );

        // --- TRAINING & SPORTS MEDICINE (no wiki links) ---
        mainContent.add(createDataPanel("Training & Sports Medicine", new String[][]{
            {"Chris Cortez", "Head Athletic Trainer"},
            {"Drew Grant", "Dir. Rehabilitation"},
            {"Tim Harkins", "Physician Assistant"},
            {"Lemar Mosley", "Athletic Trainer"},
            {"A.J. Neibel", "Head Strength & Conditioning"},
            {"Deuce Gruden", "S&C Assistant"}
        }, new String[]{"Name", "Specialty"}), "Training");

        // --- EQUIPMENT OPERATIONS (no wiki links) ---
        mainContent.add(createDataPanel("Equipment Operations", new String[][]{
            {"Bob Romanski", "Director, Equipment Ops"},
            {"Danny Molina", "Asst. Equipment Manager"},
            {"Adam Johnson", "Asst. Equipment Manager"},
            {"Deavante Hall", "Equipment Specialist"}
        }, new String[]{"Name", "Role"}), "Equipment");

        // Sidebar Setup
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(RAIDERS_BLACK);
        sidebar.setPreferredSize(new Dimension(240, 800));
        sidebar.setBorder(new EmptyBorder(20, 15, 20, 15));

        sidebar.add(loadLogo(160));
        sidebar.add(Box.createRigidArea(new Dimension(0, 30)));

        String[][] navItems = {
            {"HOME", "Home"}, {"PLAYERS", "Players"},
            {"FRONT OFFICE", "Staff"}, {"TRAINING STAFF", "Training"},
            {"EQUIPMENT STAFF", "Equipment"}
        };

        for (String[] item : navItems) {
            sidebar.add(createNavButton(item[0], item[1]));
            sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        frame.add(sidebar, BorderLayout.WEST);
        frame.add(mainContent, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private JLabel loadLogo(int size) {
        try {
            URL url = new URL("https://upload.wikimedia.org/wikipedia/en/thumb/4/48/Las_Vegas_Raiders_logo.svg/1200px-Las_Vegas_Raiders_logo.svg.png");
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            Image rawImage = javax.imageio.ImageIO.read(connection.getInputStream());
            Image scaledImage = rawImage.getScaledInstance(size, size, Image.SCALE_SMOOTH);
            return new JLabel(new ImageIcon(scaledImage));
        } catch (Exception e) {
            JLabel label = new JLabel("RAIDERS");
            label.setFont(new Font("Impact", Font.BOLD, 55));
            label.setForeground(Color.WHITE);
            return label;
        }
    }

    private JButton createNavButton(String text, String cardName) {
        JButton btn = new JButton(text);
        btn.setMaximumSize(new Dimension(210, 45));
        btn.setBackground(RAIDERS_SILVER);
        btn.setForeground(RAIDERS_BLACK);
        btn.setFont(new Font("Impact", Font.PLAIN, 18));
        btn.setFocusPainted(false);
        btn.addActionListener(e -> cardLayout.show(mainContent, cardName));
        return btn;
    }

    private JPanel createHomePanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(RAIDERS_BLACK);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.gridy = 0;
        panel.add(loadLogo(300), gbc);

        gbc.gridy = 1;
        panel.add(Box.createRigidArea(new Dimension(0, 30)), gbc);

        JLabel welcome = new JLabel("Welcome Raider Nation!", SwingConstants.CENTER);
        welcome.setFont(new Font("Impact", Font.PLAIN, 56));
        welcome.setForeground(RAIDERS_SILVER);
        gbc.gridy = 2;
        panel.add(welcome, gbc);

        return panel;
    }

    private JPanel createDataPanel(String title, String[][] data, String[] cols) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel header = new JLabel(title, SwingConstants.CENTER);
        header.setFont(new Font("Impact", Font.PLAIN, 28));
        header.setOpaque(true);
        header.setBackground(RAIDERS_SILVER);
        header.setForeground(RAIDERS_BLACK);
        header.setPreferredSize(new Dimension(0, 60));

        String[][] effectiveData = data;
        String[] effectiveCols = cols;
        boolean hasWiki = false;

        // Decide if this table should have wiki links
        if (title.equals("Active Player Roster")) {
            effectiveData = addWikiColumnForPlayers(data);
            effectiveCols = new String[]{cols[0], cols[1], cols[2], cols[3], "Wiki"};
            hasWiki = true;
        } else if (title.equals("Front Office")) {
            effectiveData = addWikiColumnForFrontOffice(data);
            effectiveCols = new String[]{cols[0], cols[1], "Wiki"};
            hasWiki = true;
        }

        JTable table = new JTable(effectiveData, effectiveCols) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table.setRowHeight(35);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.getTableHeader().setBackground(RAIDERS_SILVER);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));

        if (hasWiki) {
            int wikiColIndex = effectiveCols.length - 1;
            table.getColumnModel().getColumn(wikiColIndex).setMinWidth(0);
            table.getColumnModel().getColumn(wikiColIndex).setMaxWidth(0);
            table.getColumnModel().getColumn(wikiColIndex).setWidth(0);
            
            // mouse listener for opening wiki links (double left-click or single right-click)
            table.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {

        boolean leftDouble = SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 2;
        boolean rightSingle = SwingUtilities.isRightMouseButton(e) && e.getClickCount() == 1;

        if (leftDouble || rightSingle) {
            int row = table.rowAtPoint(e.getPoint());
            if (row >= 0) {
                Object urlObj = table.getValueAt(row, wikiColIndex);
                if (urlObj != null) {
                    String url = urlObj.toString();
                    if (!url.isEmpty()) {
                        try {
                            Desktop.getDesktop().browse(new URI(url));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        }
    }
});

        }

        panel.add(header, BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        return panel;
    }

    // Add hidden wiki column for players
    private String[][] addWikiColumnForPlayers(String[][] data) {
        String[][] result = new String[data.length][data[0].length + 1];
        for (int i = 0; i < data.length; i++) {
            System.arraycopy(data[i], 0, result[i], 0, data[i].length);
            String name = data[i][0];
            String url = getPlayerWiki(name);
            result[i][data[i].length] = url; // may be null
        }
        return result;
    }

    // Add hidden wiki column for front office
    private String[][] addWikiColumnForFrontOffice(String[][] data) {
        String[][] result = new String[data.length][data[0].length + 1];
        for (int i = 0; i < data.length; i++) {
            System.arraycopy(data[i], 0, result[i], 0, data[i].length);
            String name = data[i][0];
            String url = getFrontOfficeWiki(name);
            result[i][data[i].length] = url; // may be null
        }
        return result;
    }

    // Map player names to Wikipedia URLs (only if they actually have pages)
    private String getPlayerWiki(String name) {
        switch (name) {
            case "Ashton Jeanty":
                return "https://en.wikipedia.org/wiki/Ashton_Jeanty"; // LT added
            case "Zamir White":
                return "https://en.wikipedia.org/wiki/Zamir_White"; // LT added
            case "Shedrick Jackson":
                return "https://en.wikipedia.org/wiki/Shedrick_Jackson"; // LT added
            case "AJ Cole":
                return "https://en.wikipedia.org/wiki/A._J._Cole_(punter)";
            case "Geno Smith":
                return "https://en.wikipedia.org/wiki/Geno_Smith";
            case "Daniel Carlson":
                return "https://en.wikipedia.org/wiki/Daniel_Carlson";
            case "Tyree Wilson":
                return "https://en.wikipedia.org/wiki/Tyree_Wilson";
            case "Dont'e Thornton":
                return "https://en.wikipedia.org/wiki/Dont'e_Thornton"; // LT added
            case "Jeremy Chinn":
                return "https://en.wikipedia.org/wiki/Jeremy_Chinn";
            case "Aidan O'Connell":
                return "https://en.wikipedia.org/wiki/Aidan_O%27Connell";
            case "Kenny Pickett":
                return "https://en.wikipedia.org/wiki/Kenny_Pickett";
            case "Tyler Lockett":
                return "https://en.wikipedia.org/wiki/Tyler_Lockett";
            case "Jack Bech":
                return "https://en.wikipedia.org/wiki/Jack_Bech"; // LT added
            case "Isaiah Pola-Mao":
                return "https://en.wikipedia.org/wiki/Isaiah_Pola-Mao";
            case "Eric Stokes":
                return "https://en.wikipedia.org/wiki/Eric_Stokes_(American_football)"; // NEEDS correct link
            case "Dylan Laube":
                return "https://en.wikipedia.org/wiki/Dylan_Laube"; // LT added
            case "Decamerion Richardson":
                return "https://en.wikipedia.org/wiki/Decamerion_Richardson";
            case "Darien Porter":
                return "https://en.wikipedia.org/wiki/Darien_Porter"; // LT added
            case "Tristin McCollum":
                return "https://en.wikipedia.org/wiki/Tristin_McCollum"; // LT added
            case "Terrell Edmunds":
                return "https://en.wikipedia.org/wiki/Terrell_Edmunds";
            case "Chigozie Anusiem":
                return "https://en.wikipedia.org/wiki/Chigozie_Anusiem"; // LT added
            case "Darnay Holmes":
                return "https://en.wikipedia.org/wiki/Darnay_Holmes";
            case "Raheem Mostert":
                return "https://en.wikipedia.org/wiki/Raheem_Mostert";
            case "Lonnie Johnson Jr.":
                return "https://en.wikipedia.org/wiki/Lonnie_Johnson_Jr.";
            case "Jamal Adams":
                return "https://en.wikipedia.org/wiki/Jamal_Adams";
            case "Tommy Eichenberg":
                return "https://en.wikipedia.org/wiki/Tommy_Eichenberg";
            case "Devin White":
                return "https://en.wikipedia.org/wiki/Devin_White";
            case "Carter Runyon":
                return "https://en.wikipedia.org/wiki/Carter_Runyon"; // LT added
            case "Charles Snowden":
                return "https://en.wikipedia.org/wiki/Charles_Snowden"; // LT added
            case "Jabob Bobenmoyer":
                return "https://en.wikipedia.org/wiki/Jacob_Bobenmoyer"; // LT added
            case "Malcolm Koonce":
                return "https://en.wikipedia.org/wiki/Malcolm_Koonce";
            case "Elandon Roberts":
                return "https://en.wikipedia.org/wiki/Elandon_Roberts";
            case "Cody Lindenberg":
                return "https://en.wikipedia.org/wiki/Cody_Lindenberg"; // LT added
            case "Atonio Mafi":
                return "https://en.wikipedia.org/wiki/Atonio_Mafi";
            case "Jackson Powers-Johnson":
                return "https://en.wikipedia.org/wiki/Jackson_Powers-Johnson";
            case "Jon Rhattigan":
                return "https://en.wikipedia.org/wiki/Jon_Rhattigan"; // LT added
            case "Charles Grant":
                return "https://en.wikipedia.org/wiki/Charles_Grant_(offensive_tackle)"; // LT added
            case "Alex Cappa":
                return "https://en.wikipedia.org/wiki/Alex_Cappa";
            case "Dylan Parham":
                return "https://en.wikipedia.org/wiki/Dylan_Parham";
            case "Will Putnam":
                return "https://en.wikipedia.org/wiki/Will_Putnam"; // LT added
            case "Adam Butler":
                return "https://en.wikipedia.org/wiki/Adam_Butler_(American_football)";
            case "Stone Forsythe":
                return "https://en.wikipedia.org/wiki/Stone_Forsythe";
            case "DJ Glaze":
                return "https://en.wikipedia.org/wiki/DJ_Glaze"; // LT added
            case "Kolton Miller":
                return "https://en.wikipedia.org/wiki/Kolton_Miller";
            case "Caleb Rogers":
                return "https://en.wikipedia.org/wiki/Caleb_Rogers"; // LT added
            case "Ian Thomas":
                return "https://en.wikipedia.org/wiki/Ian_Thomas_(American_football)";
            case "Alex Bachman":
                return "https://en.wikipedia.org/wiki/Alex_Bachman"; // LT added
            case "Michael Mayer":
                return "https://en.wikipedia.org/wiki/Michael_Mayer_(American_football)";
            case "Jahfari Harvey":
                return "https://en.wikipedia.org/wiki/Jahfari_Harvey"; // LT added
            case "JJ Pegues":
                return "https://en.wikipedia.org/wiki/JJ_Pegues"; // LT added
            case "Jonah Laulu":
                return "https://en.wikipedia.org/wiki/Jonah_Laulu"; // LT added
            case "Tonka Hemingway":
                return "https://en.wikipedia.org/wiki/Tonka_Hemingway"; // LT added
            case "Brock Bowers":
                return "https://en.wikipedia.org/wiki/Brock_Bowers";
            case "Maxx Crosby":
                return "https://en.wikipedia.org/wiki/Maxx_Crosby";
            case "Thomas Booker IV":
                return "https://en.wikipedia.org/wiki/Thomas_Booker";
            default:
                return null;
        }
    }

    // Map front office names to Wikipedia URLs (only if they actually have pages)
    private String getFrontOfficeWiki(String name) {
        switch (name) {
            case "Mark Davis":
                return "https://en.wikipedia.org/wiki/Mark_Davis_(American_businessman)";
            case "Sandra Douglass Morgan":
                return "https://en.wikipedia.org/wiki/Sandra_Douglass_Morgan";
            default:
                return null;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RaidersOrgAppv6::new);
    }
}
