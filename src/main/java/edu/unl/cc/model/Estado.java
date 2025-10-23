package edu.unl.cc.model;

/**
 * Enum para estados de un ticket.
 * Catálogo: EN_COLA, EN_ATENCION, EN_PROCESO, PENDIENTE_DOCS, COMPLETADO.
 * Reglas: Transiciones lógicas (e.g., EN_COLA -> EN_ATENCION al atender).
 */
public enum Estado {
    EN_COLA, EN_ATENCION, EN_PROCESO, PENDIENTE_DOCS, COMPLETADO
}