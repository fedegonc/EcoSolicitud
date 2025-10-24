package com.tcc.controller;

import com.tcc.service.MetricsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class KpiController {

    private final MetricsService metricsService;

    public KpiController(MetricsService metricsService) {
        this.metricsService = metricsService;
    }

    @GetMapping("/kpis")
    public String showDashboardDirect(Model model) {
        // Seleccionar las primeras 5 métricas disponibles y renderizar directamente
        Set<String> selectedSet = new LinkedHashSet<>();
        metricsService.availableMetrics().stream()
                .limit(5)
                .forEach(opt -> selectedSet.add(opt.key()));

        Map<String, String> results = metricsService.computeMetrics(selectedSet);
        model.addAttribute("results", results);
        model.addAttribute("selectedCount", results.size());
        model.addAttribute("max", 5);
        return "kpis-dashboard";
    }

    @PostMapping("/kpis")
    public String showDashboard(@RequestParam(name = "metrics", required = false) List<String> selected,
                                Model model) {
        Set<String> selectedSet = new LinkedHashSet<>();
        if (selected != null) {
            for (int i = 0; i < selected.size() && i < 5; i++) {
                selectedSet.add(selected.get(i));
            }
        }
        Map<String, String> results = metricsService.computeMetrics(selectedSet);
        model.addAttribute("results", results);
        model.addAttribute("selectedCount", results.size());
        model.addAttribute("max", 5);
        return "kpis-dashboard";
    }
}
