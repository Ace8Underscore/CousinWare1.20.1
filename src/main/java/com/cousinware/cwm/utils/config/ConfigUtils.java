package com.cousinware.cwm.utils.config;

import com.cousinware.cwm.client.CwmClient;
import com.cousinware.cwm.command.Command;
import com.cousinware.cwm.command.commands.Bind;
import com.cousinware.cwm.hack.Hack;
import com.cousinware.cwm.managers.FriendManager;
import com.cousinware.cwm.managers.HackManager;
import com.cousinware.cwm.utils.FriendUtil;
import com.cousinware.cwm.utils.Setting;
import com.cousinware.cwm.utils.font.CFontRenderer;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;

import java.awt.*;
import java.io.*;
import java.util.Iterator;

public class ConfigUtils {
    public File Nord;
    public File Settings;
    public String publicname;
    MinecraftClient mc = MinecraftClient.getInstance();

    /**
     * @author Finz0
     **/

    public ConfigUtils() {
        this.Nord = new File(mc.runDirectory + File.separator + "CousinWare_Modern");
        if (!this.Nord.exists()) {
            this.Nord.mkdirs();
        }

        this.Settings = new File(mc.runDirectory + File.separator + "CousinWare_Modern" + File.separator + "Settings");
        if (!this.Settings.exists()) {
            this.Settings.mkdirs();
        }

        loadMods();
        loadDrawn();
        loadBinds();
        loadPrefix();
        loadFriends();
        loadSettingsList();
        //loadFont();
        //loadHuds();
       // loadHudPos();
        //loadXray();


    }


    public void saveBinds() {
        try {
            File file = new File(this.Nord.getAbsolutePath(), "Binds.txt");
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            Iterator var3 = HackManager.getHacks().iterator();

            while (var3.hasNext()) {
                Hack hack = (Hack) var3.next();
                out.write(hack.getName() + ":" + hack.getBind());
                out.write("\r\n");
            }

            out.close();
        } catch (Exception var5) {
        }

    }

    public void loadBinds() {
        try {
            File file = new File(this.Nord.getAbsolutePath(), "Binds.txt");
            FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String line;
            while ((line = br.readLine()) != null) {
                String curLine = line.trim();
                String name = curLine.split(":")[0];
                String bind = curLine.split(":")[1];
                for (Hack h : HackManager.getHacks()) {
                    if (h != null && h.getName().equalsIgnoreCase(name)) {
                        h.setBind(Integer.parseInt(bind));


                    }
                }
            }

            br.close();
        } catch (Exception var11) {
            var11.printStackTrace();
            saveBinds();
        }

    }


    public void saveMods() {
        try {
            File file = new File(this.Nord.getAbsolutePath(), "EnabledHacks.txt");
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            Iterator var3 = HackManager.getHacks().iterator();

            while (var3.hasNext()) {
                Hack hack = (Hack) var3.next();
                if (hack.isEnabled()) {
                    out.write(hack.getName());
                    out.write("\r\n");
                }
            }

            out.close();
        } catch (Exception var5) {
        }

    }

    public void saveFriends() {
        try {
            File file = new File(this.Nord.getAbsolutePath(), "Friends.txt");
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            Iterator var3 = FriendManager.getFriends().iterator();

            while (var3.hasNext()) {
                FriendUtil f = (FriendUtil) var3.next();
                out.write(f.getName());
                out.write("\r\n");
            }

            out.close();
        } catch (Exception var5) {
        }

    }

    public void loadFriends() {
        try {
            File file = new File(this.Nord.getAbsolutePath(), "Friends.txt");
            FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            FriendManager.friends.clear();
            String line;
            while ((line = br.readLine()) != null) {
                FriendManager.addFriend(line);
            }

            br.close();
        } catch (Exception var6) {
            var6.printStackTrace();


        }

    }


 /*   public void saveGui() {
        try {
            File file = new File(this.Nord.getAbsolutePath(), "Gui.txt");
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            Iterator var3 = ClickGUI.panels.iterator();

            while(var3.hasNext()) {
                Panel p = (Panel)var3.next();
                out.write(p.title + ":" + p.x + ":" + p.y + ":" + p.extended);
                out.write("\r\n");
            }

            out.close();
        } catch (Exception var5) {
        }

    }

    public void loadGui() {
        try {
            File file = new File(this.Nord.getAbsolutePath(), "Gui.txt");
            FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String line;
            while((line = br.readLine()) != null) {
                String curLine = line.trim();
                String name = curLine.split(":")[0];
                String x = curLine.split(":")[1];
                String y = curLine.split(":")[2];
                String e = curLine.split(":")[3];
                double x1 = Double.parseDouble(x);
                double y1 = Double.parseDouble(y);
                boolean ext = Boolean.parseBoolean(e);
                Panel p = ClickGUI.frames.get(name);
                if (p != null) {
                    p.x = x1;
                    p.y = y1;
                    p.extended = ext;
                }
            }

            br.close();
        } catch (Exception var17) {
            var17.printStackTrace();
            //this.saveGui();
        }

    }

    public void saveHudComponents() {
        try {
            File file = new File(this.Nord.getAbsolutePath(), "HudComponents.txt");
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            Iterator var3 = HudComponentManager.hudComponents.iterator();

            while(var3.hasNext()) {
                Panel p = (Panel)var3.next();
                out.write(p.title + ":" + p.x + ":" + p.y + ":" + p.extended + ":" + p.isHudComponentPinned);
                out.write("\r\n");
            }

            out.close();
        } catch (Exception var5) {
        }

    }

    public void loadHudComponents() {
        try {
            File file = new File(this.Nord.getAbsolutePath(), "HudComponents.txt");
            FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String line;
            while((line = br.readLine()) != null) {
                String curLine = line.trim();
                String name = curLine.split(":")[0];
                String x = curLine.split(":")[1];
                String y = curLine.split(":")[2];
                String e = curLine.split(":")[3];
                String pin = curLine.split(":")[4];
                double x1 = Double.parseDouble(x);
                double y1 = Double.parseDouble(y);
                boolean ex = Boolean.parseBoolean(e);
                boolean pinned = Boolean.parseBoolean(pin);
                Panel p = HudComponentManager.getHudComponentByName(name);
                if (p != null) {
                    p.x = x1;
                    p.y = y1;
                    p.extended = ex;
                    p.isHudComponentPinned = pinned;
                }
            }

            br.close();
        } catch (Exception var17) {
            var17.printStackTrace();
            //this.saveHudComponents();
        }

    } */

    public void savePrefix() {
        try {
            File file = new File(this.Nord.getAbsolutePath(), "CommandPrefix.txt");
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            out.write(Command.getClientPrefix());
            out.write("\r\n");
            out.close();
        } catch (Exception var3) {
        }

    }

    public void loadPrefix() {
        try {
            File file = new File(this.Nord.getAbsolutePath(), "CommandPrefix.txt");
            FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String line;
            while ((line = br.readLine()) != null) {
                Command.setClientPrefix(line);
            }

            br.close();
        } catch (Exception var6) {
            var6.printStackTrace();
            //savePrefix();
        }

    }

    public void saveFont() {
        try {
            File file = new File(this.Nord.getAbsolutePath(), "Font.txt");
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            out.write(CwmClient.fontRenderer.getFontName() + ":" + CwmClient.fontRenderer.getFontSize());
            out.write("\r\n");
            out.close();
        } catch (Exception var3) {
        }

    }

    public void loadFont() {
        try {
            File file = new File(this.Nord.getAbsolutePath(), "Font.txt");
            FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String line;
            while ((line = br.readLine()) != null) {
                publicname = line.split(":")[0];
                String size = line.split(":")[1];
                int sizeInt = Integer.parseInt(size);
                CwmClient.fontRenderer = new CFontRenderer(new Font(publicname, Font.PLAIN, sizeInt), true, false);
                CwmClient.fontRenderer.setFont(new Font(publicname, Font.PLAIN, sizeInt));
                CwmClient.fontRenderer.setAntiAlias(true);
                CwmClient.fontRenderer.setFractionalMetrics(false);
                CwmClient.fontRenderer.setFontName(publicname);
                CwmClient.fontRenderer.setFontSize(sizeInt);
                //CousinWare.INSTANCE.fontRenderer = new CFontRendererGui(new Font(name, Font.PLAIN, sizeInt), true, false);
                CwmClient.fontRenderer.setFont(new Font(publicname, Font.PLAIN, sizeInt));
                CwmClient.fontRenderer.setAntiAlias(true);
                CwmClient.fontRenderer.setFractionalMetrics(false);
                CwmClient.fontRenderer.setFontName(publicname);

            }

            br.close();
        } catch (Exception var6) {
            var6.printStackTrace();
            //saveFont();
        }

    }


    public void saveDrawn() {
        try {
            File file = new File(this.Nord.getAbsolutePath(), "Drawn.txt");
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            Iterator var3 = HackManager.getHacks().iterator();

            while (var3.hasNext()) {
                Hack hack = (Hack) var3.next();
                out.write(hack.getName() + ":" + hack.isDrawn());
                out.write("\r\n");
            }

            out.close();
        } catch (Exception var5) {
        }

    }

    public void loadDrawn() {
        try {
            File file = new File(this.Nord.getAbsolutePath(), "Drawn.txt");
            FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String line;
            while ((line = br.readLine()) != null) {
                String curLine = line.trim();
                String name = curLine.split(":")[0];
                String isOn = curLine.split(":")[1];
                boolean drawn = Boolean.parseBoolean(isOn);
                for (Hack h : HackManager.getHacks()) {
                    if (h.getName().equalsIgnoreCase(name)) {
                        h.drawn = drawn;
                    }
                }
            }

            br.close();
        } catch (Exception var11) {
            var11.printStackTrace();
            //saveDrawn();
        }

    }


    public void loadMods() {
        try {
            File file = new File(this.Nord.getAbsolutePath(), "EnabledHacks.txt");
            FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String line;
            while ((line = br.readLine()) != null) {
                Iterator var6 = HackManager.getHacks().iterator();

                while (var6.hasNext()) {
                    Hack h = (Hack) var6.next();
                    if (h.getName().equals(line)) {
                        h.enable();
                    }
                }
            }

            br.close();
        } catch (Exception var8) {
            var8.printStackTrace();
            //this.saveHacks();
        }

    }

    public void saveHuds() {
        try {
            File file = new File(this.Nord.getAbsolutePath(), "EnabledHuds.txt");
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            //Iterator var3 = HudManager.getHuds().iterator();

            //while (var3.hasNext()) {
             //   Hud hud = (Hud) var3.next();
                //if (hud.isEnabled()) {
               //     out.write(hud.getName());
                    out.write("\r\n");
               // }
            //}

            out.close();
        } catch (Exception var5) {
        }

    }

    public void loadHuds() {
        try {
            File file = new File(this.Nord.getAbsolutePath(), "EnabledHuds.txt");
            FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String line;
            while ((line = br.readLine()) != null) {
                //Iterator var6 = HudManager.getHuds().iterator();

               // while (var6.hasNext()) {
                //    Hud h = (Hud) var6.next();
                //    if (h.getName().equals(line)) {
                     //   h.enable();
                 //   }
               // }
            }

            br.close();
        } catch (Exception var8) {
            var8.printStackTrace();
            //this.saveHacks();
        }

    }


    public void saveSettingsList() {
        File file;
        BufferedWriter out;
        Iterator var3;
        Setting i;
        try {
            file = new File(Settings.getAbsolutePath(), "Number.txt");
            out = new BufferedWriter(new FileWriter(file));
            var3 = CwmClient.settingsManager.getSettings().iterator();

            while (var3.hasNext()) {
                i = (Setting) var3.next();
                if (i.isSlider()) {
                    out.write(i.getId() + ":" + i.getValDouble() + ":" + i.getParentMod().getName() + "\r\n");
                }
            }

            out.close();
        } catch (Exception var7) {
        }

        try {
            file = new File(Settings.getAbsolutePath(), "Boolean.txt");
            out = new BufferedWriter(new FileWriter(file));
            var3 = CwmClient.settingsManager.getSettings().iterator();

            while (var3.hasNext()) {
                i = (Setting) var3.next();
                if (i.isCheck()) {
                    out.write(i.getId() + ":" + i.getValBoolean() + ":" + i.getParentMod().getName() + "\r\n");
                }
            }

            out.close();
        } catch (Exception var6) {
        }

        try {
            file = new File(Settings.getAbsolutePath(), "String.txt");
            out = new BufferedWriter(new FileWriter(file));
            var3 = CwmClient.settingsManager.getSettings().iterator();

            while (var3.hasNext()) {
                i = (Setting) var3.next();
                if (i.isCombo()) {
                    out.write(i.getId() + ":" + i.getValString() + ":" + i.getParentMod().getName() + "\r\n");
                }
            }

            out.close();
        } catch (Exception var5) {
        }

        try {
            file = new File(Settings.getAbsolutePath(), "Color.txt");
            out = new BufferedWriter(new FileWriter(file));
            var3 = CwmClient.settingsManager.getSettings().iterator();

            while (var3.hasNext()) {
                i = (Setting) var3.next();
                if (i.isColorPicker()) {
                    out.write(i.getId() + ":" + i.getValColor().getRGB() + ":" + i.getParentMod().getName() + "\r\n");
                }
            }

            out.close();
        } catch (Exception var7) {
        }

    }

    public void loadSettingsList() {
        File file;
        FileInputStream fstream;
        DataInputStream in;
        BufferedReader br;
        String line;
        String curLine;
        String name;
        String isOn;
        String m;
        Setting mod;
        int color;
        try {
            file = new File(Settings.getAbsolutePath(), "Number.txt");
            fstream = new FileInputStream(file.getAbsolutePath());
            in = new DataInputStream(fstream);
            br = new BufferedReader(new InputStreamReader(in));

            while ((line = br.readLine()) != null) {
                curLine = line.trim();
                name = curLine.split(":")[0];
                isOn = curLine.split(":")[1];
                m = curLine.split(":")[2];
                for (Hack h : HackManager.getHacks()) {
                    if (h != null && h.getName().equalsIgnoreCase(m)) {
                        mod = CwmClient.settingsManager.getSettingByID(name);
                        mod.setValDouble(Double.parseDouble(isOn));
                    }
                }
            }

            br.close();
        } catch (Exception var13) {
            var13.printStackTrace();
            //saveSettingsList();
        }

        try {
            file = new File(Settings.getAbsolutePath(), "Color.txt");
            fstream = new FileInputStream(file.getAbsolutePath());
            in = new DataInputStream(fstream);
            br = new BufferedReader(new InputStreamReader(in));

            while ((line = br.readLine()) != null) {
                curLine = line.trim();
                name = curLine.split(":")[0];
                color = Integer.parseInt(curLine.split(":")[1]);
                m = curLine.split(":")[2];
                for (Hack h : HackManager.getHacks()) {
                    if (h != null && h.getName().equalsIgnoreCase(m)) {
                        mod = CwmClient.settingsManager.getSettingByID(name);
                        mod.setValColor(new Color(color));
                    }
                }
            }

            br.close();
        } catch (Exception var13) {
            var13.printStackTrace();
            //saveSettingsList();
        }

        try {
            file = new File(Settings.getAbsolutePath(), "Boolean.txt");
            fstream = new FileInputStream(file.getAbsolutePath());
            in = new DataInputStream(fstream);
            br = new BufferedReader(new InputStreamReader(in));

            while ((line = br.readLine()) != null) {
                curLine = line.trim();
                name = curLine.split(":")[0];
                isOn = curLine.split(":")[1];
                m = curLine.split(":")[2];
                for (Hack h : HackManager.getHacks()) {
                    if (h != null && h.getName().equalsIgnoreCase(m)) {
                        mod = CwmClient.settingsManager.getSettingByID(name);
                        mod.setValBoolean(Boolean.parseBoolean(isOn));
                    }
                }
            }

            br.close();
        } catch (Exception var12) {
            var12.printStackTrace();
            //saveSettingsList();
        }

        try {
            file = new File(Settings.getAbsolutePath(), "String.txt");
            fstream = new FileInputStream(file.getAbsolutePath());
            in = new DataInputStream(fstream);
            br = new BufferedReader(new InputStreamReader(in));

            while ((line = br.readLine()) != null) {
                curLine = line.trim();
                name = curLine.split(":")[0];
                isOn = curLine.split(":")[1];
                m = curLine.split(":")[2];
                for (Hack h : HackManager.getHacks()) {
                    if (h != null && h.getName().equalsIgnoreCase(m)) {
                        mod = CwmClient.settingsManager.getSettingByID(name);
                        mod.setValString(isOn);
                    }
                }
            }

            br.close();
        } catch (Exception var11) {
            var11.printStackTrace();
            //aveSettingsList();
        }

    }

  /*  public void saveHudPos() {
        try {
            File file = new File(this.Nord.getAbsolutePath(), "HudPos.txt");
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            Iterator var3 = HudManager.getHuds().iterator();

            while (var3.hasNext()) {
                Hud hud = (Hud) var3.next();
                out.write(hud.getName() + ":" + hud.x + ":" + hud.y);
                out.write("\r\n");
            }

            out.close();
        } catch (Exception var5) {
        }

    }

    public void loadHudPos() {
        try {
            File file = new File(this.Nord.getAbsolutePath(), "HudPos.txt");
            FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String line;
            while ((line = br.readLine()) != null) {
                String curLine = line.trim();
                String name = curLine.split(":")[0];
                String x = curLine.split(":")[1];
                String y = curLine.split(":")[2];
                for (Hud h : HudManager.getHuds()) {
                    if (h.getName().equalsIgnoreCase(name)) {
                        h.x = Integer.parseInt(x);
                        h.y = Integer.parseInt(y);
                    }
                }
            }

            br.close();
        } catch (Exception var11) {
            var11.printStackTrace();
            //saveDrawn();
        }

    } */
}