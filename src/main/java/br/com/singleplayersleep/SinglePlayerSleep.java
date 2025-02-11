package br.com.singleplayersleep;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.entity.Player;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.configuration.file.FileConfiguration;

public class SinglePlayerSleep extends JavaPlugin implements Listener {
    
    private boolean controleClima;
    
    @Override
    public void onEnable() {
        // Salva a configuração padrão se não existir
        saveDefaultConfig();
        
        // Carrega as configurações
        carregarConfiguracoes();
        
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("§7SinglePlayerSleep foi ativado!");
    }
    
    @Override
    public void onDisable() {
        getLogger().info("§cSinglePlayerSleep foi desativado!");
    }
    
    private void carregarConfiguracoes() {
        // Recarrega a configuração do arquivo
        reloadConfig();
        FileConfiguration arquivoConfig = getConfig();
        
        // Obtém o valor da configuração
        controleClima = arquivoConfig.getBoolean("clear-weather", true);
        
        // Registra as configurações carregadas
        getLogger().info("Configuração carregada - Controle do clima: " + controleClima);
    }
    
    @EventHandler
    public void aoDeitarNaCama(PlayerBedEnterEvent eventoDeitar) {
        if (eventoDeitar.getBedEnterResult() == PlayerBedEnterEvent.BedEnterResult.OK) {
            Player jogadorDeitado = eventoDeitar.getPlayer();
            World mundoAtual = jogadorDeitado.getWorld();
            
            // Verifica se é noite ou está trovejando
            if (mundoAtual.getTime() >= 12541 || mundoAtual.hasStorm()) {
                // Muda a passagem do tempo
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        // Verifica se o jogador ainda está na cama
                        if (jogadorDeitado.isSleeping()) {
                            // Define o tempo para o amanhecer
                            mundoAtual.setTime(0);
                            
                            // Limpa o clima ruim
                            if (controleClima && mundoAtual.hasStorm()) {
                                mundoAtual.setStorm(false);
                                mundoAtual.setThundering(false);
                                getServer().broadcastMessage("§7" + jogadorDeitado.getName() + " dormiu, muito baiano.");
                            } else {
                                getServer().broadcastMessage("§7" + jogadorDeitado.getName() + " dormiu, muito baiano.");
                            }
                        }
                    }
                }.runTaskLater(this, 100L); // 5 segundos (100 ticks)
            }
        }
    }
    
    // Comando para recarregar a configuração (Use em casos extremos, recomendado reiniciar o servidor).
    @Override
    public boolean onCommand(org.bukkit.command.CommandSender comandante, org.bukkit.command.Command comando, String etiqueta, String[] argumentos) {
        if (comando.getName().equalsIgnoreCase("spsreload")) {
            carregarConfiguracoes();
            comandante.sendMessage("§aConfigurações recarregadas!");
            return true;
        }
        return false;
    }
}